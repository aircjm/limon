import { getToken, removeToken } from 'src/utils/project'
import { logout } from 'src/api/login'

const note = {
  state: {

  },

  actions: {

    loadTasks(ctx, params) {
      const cancel = setLoading(ctx, 'tasks')
      const taskService = new TaskService()

      return taskService.getAll({}, params)
        .then(r => {
          ctx.commit(HAS_TASKS, r.length > 0, {root: true})
          return r
        })
        .catch(e => {
          return Promise.reject(e)
        })
        .finally(() => {
          cancel()
        })

    },
    update(ctx, task) {
      const cancel = setLoading(ctx, 'tasks')

      const taskService = new TaskService()
      return taskService.update(task)
        .then(t => {
          ctx.commit('kanban/setTaskInBucket', t, {root: true})
          return Promise.resolve(t)
        })
        .catch(e => {
          return Promise.reject(e)
        })
        .finally(() => {
          cancel()
        })
    },


    // 退出系统
    LogOut ({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
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

export default note
