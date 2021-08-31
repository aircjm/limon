<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar class="bg-white-4 shadow-2">
        <q-btn flat round icon="menu"
               @click="leftDrawerOpen = !leftDrawerOpen"/>
        <div>
          <q-btn flat to="/" aria-label="Dash App"/>
          <q-btn flat label="AllTask" to="/allTask"/>
          <q-btn flat label="TAG" to="/tag"/>
          <q-btn flat label="Timer" to="/timer"/>
        </div>

        <q-space/>
        <q-btn-dropdown v-if="$q.screen.gt.xs" icon="help" flat>
          <q-list>
            <q-item clickable v-close-popup @click="jumpLink('https://next.quasar.dev/')">
              <q-item-section>
                <q-item-label>quasar v2 Docs</q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
        <q-separator vertical inset />
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
      leftDrawerOpen: true,
      miniState: true,
      loginImg: ''
    })

    const login = ref(false)

    const jumpLink = (url) => {
      window.open(url)
    }

    return {
      ...toRefs(state),
      jumpLink, login
    }

  }
}
</script>
