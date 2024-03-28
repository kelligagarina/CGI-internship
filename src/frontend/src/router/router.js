import { createRouter, createWebHistory } from 'vue-router'

import Kinokava from '../components/Kinokava.vue'
import SooviTused from '../components/SooviTused.vue'
import IstekohtadeSoovitused from '../components/IstekohtadeSoovitused.vue'

const routes = [
    { path: '/api/kinokava', component: Kinokava },
    { path: '/api/soovitaFilme', component: SooviTused },
    { path: '/api/kinosaal', component: IstekohtadeSoovitused }
  ];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router