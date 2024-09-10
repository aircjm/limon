import {createApp} from 'vue'
import './style.css'
import App from './App.vue'
import {router} from "./routes/route.ts";

const app = createApp(App);

// const meta = document.createElement('meta')
// meta.name = 'naive-ui-style'
// document.head.appendChild(meta)


app.use(router)
    .mount('#app')
