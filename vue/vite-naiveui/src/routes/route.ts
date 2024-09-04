import { createMemoryHistory, createRouter } from 'vue-router'
import AboutPage from "../pages/AboutPage.vue";
import Home from "../pages/Home.vue";
import LayoutTest from "../pages/LayoutTest.vue";
import CssFlex from "../pages/CssFlex.vue";


const routes = [
    { path: '/', component: Home },
    { path: '/about', component: AboutPage },
    { path: '/layout', component: LayoutTest },
    { path: '/css/flex', component: CssFlex },
]

export const router = createRouter({
    history: createMemoryHistory(),
    routes,
})
