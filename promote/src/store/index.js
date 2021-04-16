import {createStore} from 'vuex'
import user from "src/store/user";
import task from "src/store/task";

export default function (/* { ssrContext } */) {
  const Store = createStore({
    modules: {
      // example
      user,
      task
    },

    // enable strict mode (adds overhead!)
    // for dev mode and --debug builds only
    strict: process.env.DEBUGGING
  })

  return Store
}
