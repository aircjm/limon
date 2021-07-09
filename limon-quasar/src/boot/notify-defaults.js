import {Notify} from 'quasar'

// Notify 默认配置
Notify.setDefaults({
  color: 'primary',
  position: 'bottom',
  progress: true,
  timeout: 3000,
  textColor: 'white',
  actions: [{ icon: 'close', color: 'white' }],
})
