
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') },
      {
        path: '/task',
        component: () => import('pages/task/TaskList.vue'),
        children: [
        ]
      }
    ]
  },

  {
    path: '/login',
    component: () => import('pages/login/Login.vue')
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
