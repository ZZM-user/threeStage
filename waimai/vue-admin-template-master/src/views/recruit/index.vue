<template>
  <div class="recruit">

    <div class="recruit-header">
      <div class="recruit-header-content">
        <div class="recruit-header-img">
        </div>
      </div>
    </div>


    <div class="recruit-container">

      <div class="enter">

        <div class="enter-hd">
          <div class="desc">
            全国范围内诚招合作伙伴。诚挚邀请有实力、有本地资源的伙伴加入，共赢本地生活美好未来！
            <button class="kw-btn btn-text btn-large" type="button">
              <span class="kw-btn__inner" @click="dialogVisible=true">查看申请详情</span>

            </button>
          </div>
          <div>
            <h2>成为商家，只需三步</h2>
            <div class="steps">
              <div class="steps-icon"><img
                alt=""
                src="data:image/png;base64,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"
              >
                申请
              </div>
              <div class="steps-arrow"><img
                alt=""
                src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iMTZweCIgaGVpZ2h0PSIyOXB4IiB2aWV3Qm94PSIwIDAgMTYgMjkiIHZlcnNpb249IjEuMSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+CiAgICA8IS0tIEdlbmVyYXRvcjogU2tldGNoIDUyLjUgKDY3NDY5KSAtIGh0dHA6Ly93d3cuYm9oZW1pYW5jb2RpbmcuY29tL3NrZXRjaCAtLT4KICAgIDx0aXRsZT5jLWFycm93LWZvci1zdGVwczwvdGl0bGU+CiAgICA8ZGVzYz5DcmVhdGVkIHdpdGggU2tldGNoLjwvZGVzYz4KICAgIDxnIGlkPSJQYWdlLTIiIHN0cm9rZT0ibm9uZSIgc3Ryb2tlLXdpZHRoPSIxIiBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiPgogICAgICAgIDxnIGlkPSJwYy3lkIjkvZzllYbnlLPor7fpppbpobUt5bey5pyJ6LSm5Y+355m75b2VIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgtNTgzLjAwMDAwMCwgLTQzOC4wMDAwMDApIiBmaWxsPSIjRTRFN0VEIj4KICAgICAgICAgICAgPGcgaWQ9InN0ZXBzIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSg0MzAuMDAwMDAwLCA0MjAuMDAwMDAwKSI+CiAgICAgICAgICAgICAgICA8ZyBpZD0iYy1hcnJvdy1mb3Itc3RlcHMiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDE1My4wMDAwMDAsIDE4LjAwMDAwMCkiPgogICAgICAgICAgICAgICAgICAgIDxwb2x5Z29uIGlkPSJpY29uX2Fycm93IiBwb2ludHM9IjEuNDYwMzA0NzYgMC4zNjAyNTQ4NjcgMC40MDAwMDAwOTUgMS40MjA1NTk1MyAxMy40NzkwOTA2IDE0LjQ5OTY1MDEgMC40MDAwMDAwOTUgMjcuNTc4NzQwNiAxLjQ2MDMwNDc2IDI4LjYzOTU0NTIgMTUuNTk5NyAxNC40OTk2NTAxIj48L3BvbHlnb24+CiAgICAgICAgICAgICAgICA8L2c+CiAgICAgICAgICAgIDwvZz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPg=="
              ></div>
              <div class="steps-icon"><img
                alt="" src="//shadow.elemecdn.com/faas/daili-recruit-gray/static/img/examine.19616be.png"
              >
                审批
              </div>
              <div class="steps-arrow"><img
                alt=""
                src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPHN2ZyB3aWR0aD0iMTZweCIgaGVpZ2h0PSIyOXB4IiB2aWV3Qm94PSIwIDAgMTYgMjkiIHZlcnNpb249IjEuMSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+CiAgICA8IS0tIEdlbmVyYXRvcjogU2tldGNoIDUyLjUgKDY3NDY5KSAtIGh0dHA6Ly93d3cuYm9oZW1pYW5jb2RpbmcuY29tL3NrZXRjaCAtLT4KICAgIDx0aXRsZT5jLWFycm93LWZvci1zdGVwczwvdGl0bGU+CiAgICA8ZGVzYz5DcmVhdGVkIHdpdGggU2tldGNoLjwvZGVzYz4KICAgIDxnIGlkPSJQYWdlLTIiIHN0cm9rZT0ibm9uZSIgc3Ryb2tlLXdpZHRoPSIxIiBmaWxsPSJub25lIiBmaWxsLXJ1bGU9ImV2ZW5vZGQiPgogICAgICAgIDxnIGlkPSJwYy3lkIjkvZzllYbnlLPor7fpppbpobUt5bey5pyJ6LSm5Y+355m75b2VIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSgtNTgzLjAwMDAwMCwgLTQzOC4wMDAwMDApIiBmaWxsPSIjRTRFN0VEIj4KICAgICAgICAgICAgPGcgaWQ9InN0ZXBzIiB0cmFuc2Zvcm09InRyYW5zbGF0ZSg0MzAuMDAwMDAwLCA0MjAuMDAwMDAwKSI+CiAgICAgICAgICAgICAgICA8ZyBpZD0iYy1hcnJvdy1mb3Itc3RlcHMiIHRyYW5zZm9ybT0idHJhbnNsYXRlKDE1My4wMDAwMDAsIDE4LjAwMDAwMCkiPgogICAgICAgICAgICAgICAgICAgIDxwb2x5Z29uIGlkPSJpY29uX2Fycm93IiBwb2ludHM9IjEuNDYwMzA0NzYgMC4zNjAyNTQ4NjcgMC40MDAwMDAwOTUgMS40MjA1NTk1MyAxMy40NzkwOTA2IDE0LjQ5OTY1MDEgMC40MDAwMDAwOTUgMjcuNTc4NzQwNiAxLjQ2MDMwNDc2IDI4LjYzOTU0NTIgMTUuNTk5NyAxNC40OTk2NTAxIj48L3BvbHlnb24+CiAgICAgICAgICAgICAgICA8L2c+CiAgICAgICAgICAgIDwvZz4KICAgICAgICA8L2c+CiAgICA8L2c+Cjwvc3ZnPg=="
              ></div>
              <div class="steps-icon"><img
                alt=""
                src="data:image/png;base64,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"
              >
                开店
              </div>
            </div>
          </div>
        </div>

        <!-- 注册列表 -->
        <div class="enter-register">

          <el-form ref="form" :model="form" :rules="rules" label-width="140px">

            <div v-show="step===1">
              <el-form-item label="联系人手机号" prop="loginName">
                <el-input v-model="form.loginName"></el-input>

                <div class="mix-input-btn">
                  <el-link :disabled="btnSendVisable" @click="sendSms">{{ smsinfo }}</el-link>
                </div>
              </el-form-item>

              <el-form-item label="验证码" prop="code">
                <el-input v-model="form.code"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button :disabled="loginDisabled" type="primary" @click="btnNext">立即注册</el-button>
              </el-form-item>

              <el-form-item>
                <el-checkbox v-model="agree">
                  我已阅读并同意
                  <a class="el-link el-link--primary is-underline" href="/#/safety-policy" target="_blank"
                  >华信商家版隐私政策</a>
                </el-checkbox>

              </el-form-item>
            </div>

            <div v-show="step===2">

              <el-form-item label="商家名称" prop="name">
                <el-input v-model="form.name" placeholder="商家名称"/>
              </el-form-item>

              <el-form-item label="联系电话" prop="phone">
                <el-input v-model="form.phone" placeholder="联系电话"/>
              </el-form-item>

              <el-form-item label="密码" prop="loginPwd">
                <el-input v-model="form.loginPwd" placeholder="密码" type="password"/>
              </el-form-item>

              <el-form-item label="确认密码" prop="reLoginPwd">
                <el-input v-model="form.reLoginPwd" placeholder="确认密码" type="password"/>
              </el-form-item>

              <el-form-item label="所在城市">
                <el-cascader v-model="value" :options="options" placeholder="所在城市" style="margin-left: -275px"/>
              </el-form-item>

              <el-form-item label="地图位置" prop="longitude" style="text-align:left;">
                <span>经度:{{ form.longitude }} 纬度:{{ form.latitude }}</span>
                <el-button type="primary" @click="mapShow=true">选择位置</el-button>

                <div style="display:none;">
                  <el-input v-model="form.longitude"/>
                </div>
              </el-form-item>

              <el-form-item label="商家地址" prop="address">
                <el-input v-model="form.address" placeholder="商家地址"/>
              </el-form-item>

              <el-form-item label="商家图片" prop="picture">
                <el-upload
                  :before-upload="beforeAvatarUpload"
                  :on-success="handleAvatarSuccess"
                  :show-file-list="false"
                  action="http://localhost:8080/image/upload"
                  class="avatar-uploader"
                >
                  <img v-if="form.picture" :src="form.picture" class="avatar" width="350">
                  <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>

                <div style="display:none;">
                  <el-input v-model="form.picture"/>
                </div>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="btnSave">提交信息</el-button>
              </el-form-item>

            </div>

            <div v-show="step===3">
              <el-result icon="success" subTitle="请耐心等待审核结果,会以短信通您." title="申请信息已发送">
              </el-result>
            </div>


          </el-form>

        </div>

      </div>

    </div>


    <!-- 申查申请 -->
    <el-dialog :visible.sync="dialogVisible" title="申请详情" width="450px">
      <div class="apply-detail">
        <div class="apply-detail-item">
          <h2>
            合作商申请基本条件：
          </h2>
          <ul>
            <li>
              有正规业务经营的公司资质，盈利状况良好；
            </li>
            <li>
              熟悉当地城市餐饮行业发展状况，在当地拥有良好的口碑资源；
            </li>
            <li>
              在当地拥有较强的政府人脉关系及宣传推广资源；
            </li>
            <li>
              较强的运营能力及成熟的行业经验；
            </li>
            <li>
              具备较强的经济实力；
            </li>
          </ul>
        </div>
        <div class="apply-detail-item">
          <h2>
            报名须知：
          </h2>
          <ul>
            <li>
              请详细阅读合作商申请基本条件；
            </li>
            <li>
              请确保您填写的信息真实、有效，如在申请过程中存在虚假信息，该平台有权随时终止合作流程；
            </li>
            <li>
              填写过程需要您用心填写，时间约2分钟；
            </li>
            <li>
              如您已经阅读完成，请继续您的报名！
            </li>
          </ul>
        </div>
      </div>

    </el-dialog>

    <!-- 地图选择器-->
    <el-dialog :close-on-click-modal="false" :visible.sync="mapShow" append-to-body title="位置选择器" width="800px">
      <BaiduAddress :itemData="form" v-on:myclick="handlerLocation"></BaiduAddress>
    </el-dialog>

  </div>
</template>

<script>
import { isPhone } from '@/utils/validate.js'
import regUtil from '@/utils/regexpUtil.js'
import { checkFrontPhoneIsExists, frontsave } from '@/api/enterprise.js'
import { checkSms, sendSmsApi, validate } from '@/api/sms'
import BaiduAddress from '@/components/BaiduAddress/index.vue'
import CityList from '@/utils/city.json'

export default {
  // 挂载地图级组件
  components: {
    BaiduAddress
  },
  data() {
    // 手机号是否已经被用过
    const phoneExists = (rule, value, callback) => {
      checkFrontPhoneIsExists(value).then(resp => {
        console.log(resp)
        console.log(this)
        if (resp.data) {
          this.btnSendVisable = true
          callback(new Error('该账户已被注册'))
        } else {
          this.btnSendVisable = false
          callback()
        }
      })
    }

    // 验证密码是否相同
    const pwdConfirmFunction = (rule, value, callback) => {
      if (value === this.form.loginPwd) {
        callback()
      } else {
        callback(new Error('两次输入的密码不一致'))
      }
    }

    return {
      value: [],
      options: CityList,
      // 百度地图
      mapShow: false,
      // 当前步骤
      step: 1,
      // 倒计时
      countDownNum: 0,
      // 是否同意
      agree: false,
      dialogVisible: false,
      smsinfo: '发送验证码',
      // 发送短信按钮是否禁用(true禁用，false启用 )
      btnSendVisable: true,
      form: {
        picture: undefined,
        address: undefined,
        latitude: undefined,
        longitude: undefined
      },
      rules: {
        loginName: [
          { required: true, message: '该值不能为空', trigger: 'blur' },
          { validator: regUtil.phone, trigger: 'blur' },
          { validator: phoneExists, trigger: 'blur' }
        ],
        code: [
          { required: true, message: '该值不能为空', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '该值不能为空', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '该值不能为空', trigger: 'blur' },
          { validator: regUtil.phone, trigger: 'blur' }
        ],
        address: [
          { required: true, message: '该值不能为空', trigger: 'blur' }
        ],
        longitude: [
          { required: true, message: '该值不能为空', trigger: 'blur' }
        ],
        picture: [
          { required: true, message: '该值不能为空', trigger: 'blur' }
        ],
        loginPwd: [
          { required: true, message: '该值不能为空', trigger: 'blur' },
          { min: 4, max: 30, message: '密码长度在4-30个字符之间', trigger: 'blur' }
        ],
        reLoginPwd: [
          { required: true, message: '该值不能为空', trigger: 'blur' },
          { validator: pwdConfirmFunction, trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    // 发送验证码
    sendSms() {
      if (!this.agree) {
        this.$message.error('请勾选并阅读华信商家版隐私政策')
        return
      }

      // 查看是否发送过短信
      checkSms(this.form.loginName).then(resp => {
        if (resp.data) {
          this.$message.error(resp.message)
        } else {
          // 发送短信
          sendSmsApi(this.form.loginName).then(resp => {
            //发送短信禁用，导计时
            this.btnSendVisable = true
            // 2分钟
            this.countDownNum = 120
            this.smsinfo = '(' + this.countDownNum + ')秒后重新发送'
            const tId = setInterval(() => {
              this.countDownNum--
              if (this.countDownNum === 0) {
                // 重新发送按钮启用
                this.btnSendVisable = false
                this.smsinfo = '发送验证码'
                clearInterval(tId)
              } else {
                this.smsinfo = '(' + this.countDownNum + ')秒后重新发送'
              }

            }, 1000)
          })
        }
      })
    },
    // 地图位置注册事件
    handlerLocation(resp) {
      console.log(resp)
      this.form.address = resp.address
      this.form.longitude = resp.longitude
      this.form.latitude = resp.latitude
      this.mapShow = false
    },
    // 图片上传成功以后返回的数据(json, file[blob]数据)
    handleAvatarSuccess(res, file) {
      this.form.picture = res.data.url
    },
    // 图片上传之前验证
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt1M = file.size / 1024 / 1024 < 1

      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG、PNG 格式!')
      }
      if (!isLt1M) {
        this.$message.error('上传头像图片大小不能超过 1MB!')
      }
      return isJPG && isLt1M
    },
    // 立即注册
    btnNext() {
      // 验证输入的验证码是否正确
      validate(this.form).then(resp => {
        // 正确(进入到录入商家信息页面)
        this.step = 2
      })
    },
    // 提交保存商家信息
    btnSave() {
      // 验证数据有效性
      this.$refs['form'].validate(valid => {
        if (valid) {
          // 保存
          frontsave(this.form).then(resp => {
            this.step = 3
          })
        }

      })

    }
  },
  computed: {
    // 登录按钮是否禁用
    loginDisabled() {
      if (!this.agree) {
        return true
      }
      // 如果不为空
      if (this.form.loginName) {
        // 再验证手机号码格式是否正确，如果正确
        if (isPhone(this.form.loginName)) {
          return false
        } else {
          // 格式不正确
          return true
        }
      }
      return true
    }
  },
  created() {

  }

}
</script>

<style lang="scss" scoped>
.recruit-header {
  background: #009bff;
  background: -webkit-gradient(linear, left top, right top, from(#00adff), to(#0082ff));
  background: linear-gradient(90deg, #00adff, #0082ff);
}

.recruit-header-content {
  position: relative;
  width: 940px;
  margin: 0 auto;
  height: 200px;
}

.recruit-header-img {
  position: absolute;
  left: -250px;
  width: 1440px;
  height: 100%;
  background: url(https://img.alicdn.com/imgextra/i1/O1CN01B3IqWN1lKuFrQqyvs_!!6000000004801-2-tps-5760-800.png) 50% no-repeat;
  background-size: cover;
}

.recruit-container {
  width: 940px;
  margin: 0 auto;
}


.enter {
  padding: 16px 16px 0;
  background: #fff;
  max-width: 652px;
  margin: 0 auto;
}

.recruit-container > div {
  min-height: calc(100vh - 200px);
}

.enter-hd {
  text-align: center;
}

.desc {
  text-align: left;
  background: #f5f7fa;
  border-radius: 6px;
  font-size: 13px;
  line-height: 22px;
  color: #909399;
  padding: 15px;
  margin-bottom: 32px;
}

.kw-breadcrumb-item,
.kw-btn,
.kw-checkbox .kw-checkbox-appearance,
.kw-link,
.kw-modal,
.kw-pager li,
.kw-pagination-size,
.kw-pagination-total,
.kw-radio .kw-radio-appearance,
.kw-tooltip {
  display: -webkit-inline-box;
  display: -ms-inline-flexbox;
  display: inline-flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -ms-flex-direction: row;
  flex-direction: row;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
}

.kw-btn {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  margin: 0;
  border: 1px solid transparent;
  cursor: pointer;
  outline: none;
  font-weight: 500;
  text-decoration: none;
  vertical-align: middle;
}


.kw-btn.btn-large {
  height: 40px;
  line-height: 38px;
  padding: 0 20px;
  border-radius: 4px;
  font-size: 14px;
}

.kw-btn.btn-text {
  color: #606266;
  background-color: transparent;
  padding: 0;
  border: none;
  position: relative;
  background: none;
}

.desc .kw-btn {
  color: #606266;
  height: 22px;
  line-height: 22px;
  vertical-align: baseline;
}

.enter-hd h2 {
  color: #303133;
  font-size: 18px;
  font-weight: 400;
}

.steps {
  padding: 30px 0 40px;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-pack: distribute;
  justify-content: space-around;
  font-size: 14px;
  color: #606266;
  border-bottom: 1px solid #e4e7ed;
}

.steps-icon {
  width: 64px;
}


.steps-icon img {
  width: 100%;
  margin-bottom: 10px;
}

.steps-arrow {
  padding-top: 14px;
}

.enter-register {
  padding: 25px 0;
  text-align: center;
}

.apply-detail-item {
  padding: 12px 0;
}

.apply-detail-item:first-child {
  border-bottom: 1px solid #e4e7ed;
}

.apply-detail h2 {
  font-size: 16px;
  color: #303133;
  line-height: 16px;
  font-weight: 400;
  margin-bottom: 10px;
}

.apply-detail ul {
  padding: 0;
  list-style: none;
}

.apply-detail-item ul li {
  font-size: 14px;
  color: #606266;
  padding-left: 20px;
  line-height: 24px;
  position: relative;
}

.apply-detail-item ul li:after {
  position: absolute;
  content: "";
  width: 6px;
  height: 6px;
  border-radius: 50%;
  background: #c0c4cc;
  left: 0;
  top: 9px;
}

.apply-detail-item {
  padding: 12px 0;
}

.apply-detail h2 {
  font-size: 16px;
  color: #303133;
  line-height: 16px;
  font-weight: 400;
  margin-bottom: 10px;
}

.enter-register {
  padding: 25px 0;
  text-align: center;
}

.enter-register .mix-input-btn {
  background: #fff;
  font-size: 12px;
  position: absolute;
  right: 2px;
  top: 10px;
  color: #303133;
  font-weight: 500;
  height: 20px;
  line-height: 20px;
  padding: 0 15px;
  border-left: 1px solid #dcdfe6;
  cursor: pointer;
}

.enter-register .mix-input-btn .el-link:after {
  border-color: transparent;
}
</style>
