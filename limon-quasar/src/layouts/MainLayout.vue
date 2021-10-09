<template>
  <q-layout view="lHh Lpr fff" class="bg-grey-1">
    <q-header elevated class="bg-white text-grey-8" height-hint="58">
      <q-toolbar class="bg-white-4 shadow-2 justify-between">
        <q-btn flat dense round @click="toggleLeftDrawer" aria-label="Menu" icon="menu" class="q-mx-md"
        />
        <q-btn flat to="/" aria-label="Dash App"/>
        <q-btn flat label="AllTask" to="/allTask"/>
        <q-btn flat label="TAG" to="/tag"/>
        <q-btn flat label="Timer" to="/timer"/>

        <q-space/>
        <q-input dense v-model="search" placeholder="Search">
          <template v-slot:after>
            <q-icon v-if="search === ''" name="search" />
            <q-icon v-else name="clear" class="cursor-pointer" @click="search = ''" />
          </template>
        </q-input>
        <q-btn-dropdown v-if="$q.screen.gt.xs" icon="help" flat>
          <q-list>
            <q-item clickable v-close-popup @click="jumpLink('https://next.quasar.dev/')">
              <q-item-section>
                <q-item-label>quasar v2 Docs</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
        <q-btn v-if="login" stretch flat no-wrap>
          <q-avatar rounded size="20px">
            <img src="loginImg">
          </q-avatar>
          <q-icon name="arrow_drop_down" size="16px"/>
          <q-menu auto-close>
            <q-list>
              <q-item>
                <q-item-section>
                  <div>Signed in as <strong>Mary</strong></div>
                </q-item-section>
              </q-item>
              <q-separator separator inset/>
              <q-item clickable>
                <q-item-section>
                  <div>
                    <q-icon name="tag_faces" color="blue-9" size="18px"/>
                    Set your status
                  </div>
                </q-item-section>
              </q-item>
              <q-separator/>
              <q-item clickable>
                <q-item-section>Your profile</q-item-section>
              </q-item>
              <q-separator/>
              <q-item clickable>
                <q-item-section>Help</q-item-section>
              </q-item>
              <q-item clickable>
                <q-item-section>Settings</q-item-section>
              </q-item>
              <q-item clickable>
                <q-item-section>
                  <div>
                    <q-icon name="logout" color="blue-9" size="18px"/>
                    Sign out
                  </div>
                </q-item-section>
              </q-item>
            </q-list>
          </q-menu>
        </q-btn>
        <q-btn flat v-else icon="login"/>

      </q-toolbar>
    </q-header>


    <q-drawer
      v-model="leftDrawerOpen"
      bordered
      behavior="mobile"
      @click="leftDrawerOpen = false"
    >
      <q-scroll-area class="fit">
        <q-toolbar >
          <q-toolbar-title class="row items-center text-grey-8">
            切换到
          </q-toolbar-title>
        </q-toolbar>

        <!--菜单集合-->
        <q-list padding>
          <q-item v-for="link in links" :key="link.text" clickable class="GPL__drawer-item">
            <q-item-section avatar>
              <q-icon :name="link.icon" />
            </q-item-section>
            <q-item-section>
              <q-item-label>{{ link.text }}</q-item-label>
            </q-item-section>
          </q-item>
          <q-separator class="q-my-md" />
        </q-list>
      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script>

import {reactive, ref, toRefs} from "@vue/reactivity";


export default {
  name: 'MainLayout',
  setup(props, context) {
    const state = reactive({
      leftDrawerOpen: false,
      miniState: true,
      search: '',
      loginImg: ''
    })

    function toggleLeftDrawer () {
      state.leftDrawerOpen = !state.leftDrawerOpen.value
    }

    const login = ref(false)

    const jumpLink = (url) => {
      window.open(url)
    }

    return {
      ...toRefs(state),
      jumpLink, login, toggleLeftDrawer,
      links: [
        { icon: 'photo', text: 'Photos' },
        { icon: 'photo_album', text: 'Albums' },
        { icon: 'assistant', text: 'Assistant' },
        { icon: 'people', text: 'Sharing' },
        { icon: 'book', text: 'Photo books' }
      ]
    }

  }
}
</script>
