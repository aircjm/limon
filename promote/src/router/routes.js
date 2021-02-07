
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') },
      {
        path: '/tag',
        component: () => import('pages/tag/TagList.vue'),
        children: [
        ]
      },
      {
        path: '/task',
        component: () => import('pages/task/TaskList.vue'),
        children: [
        ]
      },
      {
        path: '/allTask',
        component: () => import('pages/task/AllTask.vue'),
        children: [
        ]
      },
      { path: '/task/edit', component: () => import('pages/task/TaskEdit.vue') }
    ]
  },

  {
    path: '/login',
    component: () => import('pages/login/Login.vue')
  },
  {
    path: '/mark',
    component: () => import('components/editor/MarkdownEditor')
  },
  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
