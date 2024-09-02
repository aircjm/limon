import { createMemoryHistory, createRouter } from 'vue-router'
import AboutPage from "../pages/AboutPage.vue";
import Home from "../pages/Home.vue";


const routes = [
    { path: '/', component: Home },
    { path: '/about', component: AboutPage },
]

export const router = createRouter({
    history: createMemoryHistory(),
    routes,
})
