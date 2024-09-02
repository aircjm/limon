import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import {router} from "./routes/route.ts";

createApp(App)
    .use(router)
    .mount('#app')
