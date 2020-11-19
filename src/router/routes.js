
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') },
      {
        path: '/record',
        component: () => import('pages/task/TaskList.vue'),
        children: [
        ]
      },
      { path: '/record/edit', component: () => import('pages/task/TaskEdit.vue') }
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
