<template>
  <body id="poster">
    <el-form class="login-container" label-position="left"
             label-width="0px">
      <h3 class="login_title">系统登录</h3>
      <el-form-item>
        <el-input type="text" v-model="loginForm.username"
                  auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password" v-model="loginForm.password"
                  auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 100%;background: #505458;border: none" v-on:click="login">登录</el-button>
      </el-form-item>
      <el-form-item style="width: 100%">
        <el-button type="text" style="margin-top: -20px;margin-left: 70%;" @click="showRegisterDialog">立即注册</el-button>
      </el-form-item>
      <!--注册弹窗-->
      <RegisterDialog   ref="register"  ></RegisterDialog>
    </el-form>
  </body>
</template>


<script>
  import RegisterDialog from './RegisterDialog'

  export default {
        name: "",
        components: {RegisterDialog},
      data () {
        return {
          loginForm: {
            username: 'admin',
            password: '123'
          },
          responseResult: []
        }
      },
      methods: {
        login () {
          var _this = this;
          console.log(this.$store.state);
          this.$axios
            .post('/login', {
              username: this.loginForm.username,
              password: this.loginForm.password
            })
            .then(successResponse => {
              if (successResponse.data.code === 200) {
                //this.$router.replace({path: '/index'})
                 _this.$store.commit('login',successResponse.data.data)
                var resultPath = this.$route.query.redirect
                resultPath =  (resultPath === '/' || resultPath === undefined) ? '/index' : resultPath
                console.log('path---->' + resultPath);
                this.$router.replace({path: resultPath})
              }
            })
            .catch(failResponse => {
            })
        },

        showRegisterDialog() {
          this.$refs.register.dialogFormVisible = true

        }

      }
    }

</script>

<style scoped>

</style>
<style>
  #poster {
    background: url("../assets/flp/login/ff14-login-06.jpg") no-repeat center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  /*设置所有外边距为0*/
  body{
    margin: 0px;
  }

  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }

  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

</style>

