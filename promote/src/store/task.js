import { removeToken } from 'src/utils/project'
import { saveTask } from 'src/api/task'

const task = {
  state: {
    task: null
  },

  actions: {
    UpdateTask ({ commit, state }) {
      return new Promise((resolve, reject) => {
        saveTask(state.task).then(() => {
          commit('SET_TOKEN', '')
          commit('SET_ROLES', [])
          commit('SET_PERMISSIONS', [])
          removeToken()
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut ({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}

export default task
