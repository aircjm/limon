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
          label="Task"
          to="/task"
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
                <q-item-label @click="jumpLink('http://www.quasarchs.com/start/pick-quasar-flavour/')">
                  Docs
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

    <!--<q-drawer
      show-if-above
      v-model="leftDrawerOpen"
      bordered
      content-class="bg-grey-1"
    >
      <q-list>
        <q-item-label
          header
          class="text-grey-8"
        >
          Essential Links
        </q-item-label>
        <EssentialLink
          v-for="link in essentialLinks"
          :key="link.title"
          v-bind="link"
        />
      </q-list>
    </q-drawer>-->

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
    title: 'Docs',
    caption: 'quasar.dev',
    icon: 'school',
    link: 'http://www.quasarchs.com/start/pick-quasar-flavour/'
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
  data () {
    return {
      leftDrawerOpen: true,
      miniState: true,
      essentialLinks: linksData
    }
  },
  methods: {
    jumpLink (url) {
      window.open(url)
    }

  }
}
</script>
