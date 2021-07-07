<template>
  <q-layout view="lHh Lpr lFf">
    <q-header elevated>
      <q-toolbar>
        <q-btn stretch flat icon="menu" aria-label="Menu"
               @click="leftDrawerOpen = !leftDrawerOpen"/>
        <q-separator dark vertical/>
        <q-btn stretch flat icon="home"  to="/" aria-label="Dash App"/>
        <q-btn stretch flat label="AllTask" to="/allTask"/>
        <q-btn stretch flat label="TAG" to="/tag"/>
        <q-btn stretch flat label="Timer" to="/timer"/>
        <q-space/>
        <q-btn-dropdown flat dense round icon="help">
          <q-list>
            <q-item clickable>
              <q-item-section>
                <q-item-label @click="jumpLink('https://next.quasar.dev/')">
                  quasar v2 Docs
                </q-item-label>
              </q-item-section>
            </q-item>
          </q-list>
        </q-btn-dropdown>
        <q-separator dark vertical/>
        <q-btn flat dense round icon="login"/>
      </q-toolbar>
    </q-header>

    <q-page-container>
      <router-view/>
    </q-page-container>
  </q-layout>
</template>

<script>

import {reactive, toRefs} from "@vue/reactivity";


export default {
  name: 'MainLayout',
  setup() {
    const state = reactive({
      leftDrawerOpen: true,
      miniState: true
    })

    const jumpLink = (url) => {
      window.open(url)
    }

    return {
      ...toRefs(state),
      jumpLink
    }

  }
}
</script>
