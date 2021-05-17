<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
          icon="menu"
          aria-label="Menu"
          @click="leftDrawerOpen = !leftDrawerOpen"
        />
        <q-btn
          flat
          to="/"
        >
          Dash App
        </q-btn>
        <q-separator
          dark
          vertical
        />
        <q-btn
          stretch
          flat
          label="AllTask"
          to="/alltask"
        />
        <q-btn
          stretch
          flat
          label="TAG"
          to="/tag"
        />
        <q-space />
        <q-btn-dropdown
          flat
          dense
          round
          icon="help"
        >
          <q-list>
            <q-item clickable>
              <q-item-section>
                <q-item-label @click="jumpLink('https://next.quasar.dev/')">
                  quasar Docs
                </q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
        <q-separator
          dark
          vertical
        />
        <q-btn
          flat
          dense
          round
          icon="login"
          aria-label="用户中心"
        />
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>

const linksData = [
  {
    title: 'Task List',
    caption: '任务列表',
    icon: 'code',
    link: '/#/task'
  },
  {
    title: 'Quasar Docs',
    caption: 'quasar.dev',
    icon: 'help',
    link: 'https://next.quasar.dev/'
  },
  {
    title: 'Discord Chat Channel',
    caption: 'chat.quasar.dev',
    icon: 'chat',
    link: 'https://chat.quasar.dev'
  },
  {
    title: 'Forum',
    caption: 'forum.quasar.dev',
    icon: 'record_voice_over',
    link: 'https://forum.quasar.dev'
  },
  {
    title: 'Quasar Awesome',
    caption: 'Community Quasar projects',
    icon: 'favorite',
    link: 'https://awesome.quasar.dev'
  }
]

export default {
  name: 'MainLayout',
  data() {
    return {
      leftDrawerOpen: true,
      miniState: true,
      essentialLinks: linksData
    }
  },
  methods: {
    jumpLink(url) {
      window.open(url)
    }

  }
}
</script>
