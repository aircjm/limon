<template>
  <q-layout>
    <q-page-container>
      <q-page class="flex bg-image flex-center">
        <q-card :style="$q.screen.lt.sm?{'width': '80%'}:{'width':'30%'}">
          <q-card-section>
            <q-avatar
              size="103px"
              class="absolute-center shadow-10"
            >
              <img src="../../statics/profile.svg">
            </q-avatar>
          </q-card-section>
          <q-card-section>
            <div class="text-center q-pt-lg">
              <div class="col text-h6 ellipsis">
                Log in
              </div>
            </div>
          </q-card-section>
          <q-card-section>
            <q-form
              class="q-gutter-md column"
            >
              <q-input
                filled
                v-model="loginForm.username"
                label="Username"
                lazy-rules
              />
              <q-input
                type="password"
                filled
                v-model="loginForm.password"
                label="Password"
                lazy-rules
              />
              <q-field>
                <q-input
                  filled
                  v-model="loginForm.code"
                  label="code"
                  lazy-rules
                  style="max-width: 50%"
                />
                <q-img
                  :src="codeUrl"
                  @click="getCode"
                  style="height: 56px; max-width: 150px;"
                />
              </q-field>
              <q-toggle
                label="记住密码"
                v-model="loginForm.rememberMe"
                checked-icon="check"
                color="green"
                unchecked-icon="clear"
              />
              <div>
                <q-btn
                  label="Login"
                  @click="submitLogin"
                  type="button"
                  color="primary"
                />
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import { getCodeImg, login } from 'src/api/login'
import { setToken } from 'src/utils/project'

export default {
  name: 'Login',
  data () {
    return {
      codeUrl: '',
      loginForm: {
        username: '',
        password: '',
        rememberMe: false,
        code: '',
        uuid: ''
      }
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created () {
    this.getCode()
  },
  methods: {
    submitLogin () {
      login(this.loginForm).then((res) => {
        setToken(res.token)
        this.$router.push({ path: this.redirect || '/' })
      }).catch(() => {
        this.getCode()
      })
    },
    getCode () {
      getCodeImg().then(res => {
        this.codeUrl = 'data:image/gif;base64,' + res.img
        this.loginForm.uuid = res.uuid
      })
    }
  }
}
</script>

<style scoped>
.bg-image {
  background-image: linear-gradient(135deg, #7028e4 0%, #e5b2ca 100%);
}
</style>
