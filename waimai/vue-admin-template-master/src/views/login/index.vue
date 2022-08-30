<template>
  <div id="board">
    <div class="login-container">
      <div class="myBg"></div>

      <el-form ref="loginForm" :model="loginForm" :rules="loginRules" auto-complete="on" class="login-form"
               label-position="left"
      >
        <div class="title-container">
          <h3 class="title">饿美了么</h3>
        </div>
        <el-form-item prop="account">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
          <el-input
            ref="account"
            v-model.trim="loginForm.account"
            auto-complete="on"
            name="account"
            placeholder="Username"
            tabindex="1"
            type="text"
          />
        </el-form-item>

        <el-form-item prop="loginPwd">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
          <el-input
            :key="passwordType"
            ref="loginPwd"
            v-model="loginForm.loginPwd"
            :type="passwordType"
            auto-complete="on"
            name="loginPwd"
            placeholder="Password"
            tabindex="2"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"/>
        </span>
        </el-form-item>
        <el-form-item prop="code">
        <span class="svg-container">
            <svg-icon class="el-input__icon input-icon" icon-class="code"/>
        </span>
          <el-input
            v-model="loginForm.code"
            auto-complete="off"
            placeholder="验证码"
            style="width: 63%;"
            tabindex="3"
            @focus="refreshCode"
            @keyup.enter.native="handleLogin"
          >
          </el-input>
          <div class="login-code">
            <el-image
              :src="base64Image" alt="看不清，就用莎普爱思！" class="login-code-img" fit="contain"
              @click.native="getCode"
            >
            </el-image>
          </div>
        </el-form-item>
        <el-button :loading="loading" class="login-button"
                   @click.native.prevent="handleLogin"
        >登录
        </el-button>

        <div class="tips">
          <span style="margin-right:20px;">username: 123456 or 13565421563</span>
          <span> password: 123456</span>
        </div>
        <div class="tips">
          <router-link :to="{path:'recruit'}" class="el-link" target="_blank">我要入驻</router-link>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
import { getCaptcha } from '@/api/user'
import { Message } from 'element-ui'

export default {
  name: 'Login',
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 6) {
        callback(new Error('密码至少为6位字符组成'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        account: '123456',
        loginPwd: '123456',
        code: undefined,
        uuid: undefined
      },
      loginRules: {
        account: [{ required: true, trigger: 'blur' }],
        loginPwd: [{ message: '密码至少为6位字符组成', required: true, trigger: 'blur', validator: validatePassword }],
        code: [{ message: '验证码不能为空', required: true, trigger: 'blur' }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      // 验证码
      base64Image: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    this.getCode()
  },
  methods: {
    refreshCode() {
      if (!this.base64Image) {
        this.getCode()
      }
    },
    getCode() {
      const params = { width: 150, height: 52 }
      getCaptcha(params).then(resp => {
        this.base64Image = resp.data.img
        this.loginForm.uuid = resp.data.uuid
      })
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$router.push({ path: this.redirect || '/' })
            this.loading = false
          }).catch((message) => {
            Message({
              message: message || 'Error',
              type: 'error',
              duration: 5 * 1000
            })
            this.loading = false
            this.getCode()
          })
        } else {
          console.log('error submit!!')
          this.getCode()
          return false
        }
      })
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #409EFF;
$light_gray: rgba(0, 0, 0, 0.7);
$cursor: #FFF;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

#board {
  width: 100%;
  height: 100%;
  background: linear-gradient(to left, #F9BA36, #A9CEF0, #409EFF);
  background-size: auto 100%;
  position: absolute;
}

/* reset element-ui css */
.login-container {

  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0;
      -webkit-appearance: none;
      border-radius: 0;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      font-weight: 500;
      font-size: large;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    background: rgba(248, 248, 248, 1);
    border-radius: 5px;
    color: #f8f8f8;
    border: 1px solid rgba(255, 255, 255, 0.6);
  }

  .login-code-img {
    position: absolute;
    top: 1px;
    right: 0;
    border-radius: 0 5px 5px 0;
    cursor: pointer;
  }

  .myBg {
    width: 573px;
    height: 90%;
    position: absolute;
    margin: 0 0 0 0;
    //border-radius: 15px;
    border-radius: 15px 0 0 15px;
    background: url(../../assets/myBg.jpg) center no-repeat;
  }

  .login-button {
    width: 100%;
    margin-top: 20px;
    margin-bottom: 30px;
    background: $bg;
    color: #f8f8f8;
    font-weight: 600;
    font-size: large;
    letter-spacing: 20px;

    border: 4px solid #f8f8f8;
  }

  .login-button:hover {
    //background: rgba(255, 255, 255, 0.6);
    //color: $bg;
    //border: 2px $bg;
  }

  .login-container .el-image__error {
    margin-top: 8px;
    margin-right: 16px;
  }
}
</style>

<style lang="scss" scoped>
$bg: #409EFF;
$light_gray: rgba(0, 0, 0, 0.7);
$dark_gray: #889aa4;

.login-container {
  min-height: 90%;
  width: 80%;
  margin: 2.5% auto 0;
  background-color: $bg;
  overflow: hidden;
  border-radius: 15px;
  box-shadow: 2px 2px 2px 1px;

  .login-form {
    width: 520px;
    max-width: 100%;
    position: relative;
    padding: 50px 35px 50px;
    margin: 4% 0 0 52%;
    overflow: hidden;
    //border: 0.2px solid #f8f8f8;
    //box-shadow: 0 2px 3px 0;
    border-radius: 15px;
  }

  .tips {
    font-size: 14px;
    color: rgba(255, 255, 255, 0.8);
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;

    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 50px;
      color: #f8f8f8;
      margin: 0 auto 40px auto;
      text-align: center;
      font-weight: bold;
      cursor: pointer;
    }
  }

  .show-pwd {
    position: absolute;
    right: 2%;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
