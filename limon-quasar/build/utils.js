import fs from 'fs'
import path from 'path'
import dotenv from 'dotenv'

const httpsReg = /^https:\/\//

export function wrapperEnv(envOptions) {
  if (!envOptions) return {}
  const ret = {}

  for (const key in envOptions) {
    let val = envOptions[key]
    if (['true', 'false'].includes(val)) {
      val = val === 'true'
    }
    if (['VITE_PORT'].includes(key)) {
      val = +val
    }
    if (key === 'VITE_PROXY' && val && typeof val === 'string') {
      try {
        val = JSON.parse(val.replace(/'/g, '"'))
      } catch (error) {
        val = ''
      }
    }
    ret[key] = val
    if (typeof val === 'string') {
      process.env[key] = val
    } else if (typeof val === 'object') {
      process.env[key] = JSON.stringify(val)
    }
  }
  return ret
}

export function createProxy(list = []) {
  const ret = {}
  for (const [prefix, target] of list) {
    const isHttps = httpsReg.test(target)

    // https://github.com/http-party/node-http-proxy#options
    ret[prefix] = {
      target: target,
      changeOrigin: true,
      ws: true,
      rewrite: (path) => path.replace(new RegExp(`^${prefix}`), ''),
      // https is require secure=false
      ...(isHttps ? { secure: false } : {}),
    }
  }
  return ret
}

/**
 * 获取当前环境下生效的配置文件名
 */
function getConfFiles() {
  const script = process.env.npm_lifecycle_script
  const reg = new RegExp('--mode ([a-z_\\d]+)')
  const result = reg.exec(script)
  if (result) {
    const mode = result[1]
    return ['.env', '.env.local', `.env.${mode}`]
  }
  return ['.env', '.env.local', '.env.production']
}

export function getEnvConfig(match = 'VITE_APP_GLOB_', confFiles = getConfFiles()) {
  let envConfig = {}
  confFiles.forEach((item) => {
    try {
      if (fs.existsSync(path.resolve(process.cwd(), item))) {
        const env = dotenv.parse(fs.readFileSync(path.resolve(process.cwd(), item)))
        envConfig = { ...envConfig, ...env }
      }
    } catch (e) {
      console.error(`Error in parsing ${item}`, e)
    }
  })
  const reg = new RegExp(`^(${match})`)
  Object.keys(envConfig).forEach((key) => {
    if (!reg.test(key)) {
      Reflect.deleteProperty(envConfig, key)
    }
  })
  return envConfig
}

export function getRootPath(...dir) {
  return path.resolve(process.cwd(), ...dir)
}
