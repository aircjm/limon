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
                v-model="username"
                label="Username"
              />
              <q-input
                type="password"
                filled
                v-model="password"
                label="Password"
              />
<!--              <q-field>
                <q-input
                  filled
                  v-model="code"
                  label="code"
                  style="max-width: 50%"
                />
                <q-img
                  :src="codeUrl"
                  @click="getCode"
                  style="height: 56px; max-width: 150px;"
                />
              </q-field>-->
              <q-toggle
                label="记住密码"
                v-model="rememberMe"
                checked-icon="check"
                color="green"
                unchecked-icon="clear"
              />
              <div>
                <q-btn
                  label="Login"
                  @click="submitLogin"
                  @keyup.enter="submitLogin"
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
import {getCodeImg, login} from 'src/api/login'
import {setToken} from 'src/utils/project'
import {reactive, ref, toRefs} from "@vue/reactivity";
import {onMounted} from "@vue/runtime-core";
import {useRoute, useRouter} from "vue-router";

export default {
  name: 'Login',
  setup() {
    const codeUrl = ref('')


    const router = useRouter();
    const route = useRoute()

    const  redirect = ref( route.query && route.query.redirect)


    onMounted(() => {
      getCode();
    })

    const getCode = () => {
      getCodeImg().then(res => {
        codeUrl.value = 'data:image/gif;base64,' + res.img
        loginForm.uuid = res.uuid
      })
    }

    const loginForm = reactive({
      username: '',
      password: '',
      rememberMe: false,
      code: '',
      uuid: ''
    });

    const submitLogin = () => {
        login(loginForm).then((res) => {
          setToken(res.token)
          router.push({path: redirect || '/'})
        }).catch(() => {
           getCode()
        })
    }

    return {
     ...toRefs(loginForm), submitLogin, codeUrl, redirect, getCode
    }
  }
}
</script>

<style scoped>
.bg-image {
  background-image: linear-gradient(135deg, #7028e4 0%, #e5b2ca 100%);
}
</style>
