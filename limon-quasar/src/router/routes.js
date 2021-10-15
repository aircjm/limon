import Error404 from "pages/Error404";

export default [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      {path: '', component: () => import('pages/Index.vue')},
      {
        path: '/tag',
        component: () => import('pages/tag/TagList.vue'),
        children: []
      },{
        path: '/note',
        component: () => import('pages/note/AllNote.vue'),
        children: []
      },
      {
        path: '/timer',
        component: () => import('pages/timer/Timer.vue'),
        children: []
      },
      {
        path: '/allTask',
        component: () => import('pages/task/AllTask.vue'),
        children: []
      },
      {path: '/task/edit', component: () => import('pages/task/TaskEdit.vue')}
    ]
  },
  {
    path: '/login',
    component: () => import('pages/login/Login.vue')
  },
  {
    path: '/mark',
    component: () => import('components/editor/MarkdownEditor.vue')
  },
  {
    path: '/test',
    component: () => import('pages/task/Test.vue')
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: Error404
  }
]
