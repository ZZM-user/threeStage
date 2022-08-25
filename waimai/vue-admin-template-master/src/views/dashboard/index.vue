<template>
  <div class="dashboard-container">

    <div class="dashboard">

      <el-row :gutter="20" class="info">
        <el-col :span="6">
          <div class="info01 item-wrap">
            <span>{{ allNum }}</span>
            <p>会员总数量</p>
          </div>
        </el-col>

        <el-col :span="6">
          <div class="info02 item-wrap">
            <span>{{ countProductNum }}</span>
            <p>菜品总数量</p>
          </div>
        </el-col>

        <el-col :span="6">
          <div class="info03 item-wrap">
            <span>24</span>
            <p>成交总金额</p>
          </div>
        </el-col>

        <el-col :span="6">
          <div class="info04 item-wrap">
            <span>24</span>
            <p>交易笔数</p>
          </div>
        </el-col>

      </el-row>

      <el-row :gutter="10" class="info">
        <el-col :span="18">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <span class="box-title">会员注册数</span>
              <ul class="hy">
                <li :class="{active:(hyIndex===1)}" @click="hyIndex=1">本周</li>
                <li :class="{active:(hyIndex===2)}" @click="hyIndex=2">本月</li>
                <li :class="{active:(hyIndex===3)}" @click="hyIndex=3">全年</li>
              </ul>
            </div>
            <div ref="chart01" style="width:100%;height:380px"/>
          </el-card>

          <el-card class="box-card mt10">
            <div slot="header" class="clearfix">
              <span class="box-title">商家注册数</span>
            </div>

            <div ref="chart02" style="width:100%;height:320px"/>
          </el-card>

        </el-col>

        <!-- 右边 -->
        <el-col :span="6">

          <div class="r03 ritem">
            <div class="title">待审核的商家数</div>
            <div class="numdiv"><span>{{ notApprovedNum }}</span>笔</div>
          </div>

          <div class="r01 ritem">
            <div class="title">本月新注册会员</div>
            <div class="numdiv"><span>{{ currentMonthNum }}</span>人</div>
          </div>

          <div class="r02 ritem">
            <div class="title">未成交订单数</div>
            <div class="numdiv"><span>1</span>笔</div>
          </div>

          <div class="r04 ritem">
            <div class="title">当日菜品卖出</div>

            <div ref="chart03" class="sellorder"/>
          </div>
        </el-col>
      </el-row>

    </div>

  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import * as echarts from 'echarts'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters(['name'])
  },
  data() {
    return {
      timer: undefined,
      nowTime: new Date(),
      // 所有会员数量
      allNum: 0,
      currentMonthNum: 0,
      // 未审核的商家数量
      notApprovedNum: 0,
      // 菜品总数量
      countProductNum: 0,
      // 会员报表（1本周，2本月，3今年）
      hyIndex: 1
    }
  },
  mounted() {
    this.initDay()
  },
  methods: {
    initDay() {
      const myChart = echarts.init(this.$refs.chart01)
      let option

      option = {
        xAxis: {
          type: 'category',
          data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: 'line'
          }
        ]
      }

      option && myChart.setOption(option)
    }
  },
  watch: {
    hyIndex: {
      handler() {
        this.initDay()
      }
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    background-color: #f9f2ec;
    min-height: calc(100vh - 50px);
  }

  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.dashboard {
  position: relative;
  width: 100%;
  margin: 0 auto;
  padding: 25px;
  background-color: #f9f2ec;
}

.d-chart-item,
.d-condition-item {
  position: absolute;
}

.titlediv {
  width: 322px;
  left: 40%;
  top: 20px;
  z-index: 52;
  pointer-events: auto;
}

.simple-text {
  width: 100%;
  font-family: 'Microsoft Yahei', Arial, sans-serif;
  font-size: 28px;
  color: rgb(255, 255, 255);
  text-shadow: rgb(255, 255, 255) 0px 0px 15px;
  font-weight: bold;
  justify-content: center;
  text-align: center;
  padding-top: 10px;
}

.timer {
  width: 223px;
  height: 48px;
  right: 20px;
  top: 30px;
  z-index: 53;
  pointer-events: auto;
}

.info {
  padding-bottom: 20px;
}

.info01 {
  background-image: url(../../assets/info01.jpg);
}

.info02 {
  background-image: url(../../assets/info02.jpg);
}

.info03 {
  background-image: url(../../assets/info03.jpg);
}

.info04 {
  background-image: url(../../assets/info04.jpg);
}

.item-wrap {
  background-repeat: no-repeat;
  //width: 300px;
  height: 140px;
  margin: 0 auto;
  color: #fff;
  border-radius: 25px;
  text-align: center;
}

.item-wrap span {
  font-size: 38px;
  color: #fff;
  display: block;
  padding-top: 25px;
  font-family: 黑体, sans-serif;
}

.item-wrap p {
  font-size: 24px;
  color: rgba(255, 255, 255, 0.6);
}

.box-card {
  border-radius: 20px;
  margin: 0 10px;
  position: relative;
}

.mt10 {
  margin-top: 10px;
}

.box-title {
  font-weight: bold;
  font-size: 18px;
}

.hy {
  position: absolute;
  right: 30px;
  top: 0;
  list-style: none;
}

.hy li {
  float: left;
  color: #9e9e9e;
  padding: 5px 10px;
  border: 1px solid #9e9e9e;
  margin-left: 10px;
  font-weight: bold;
  border-radius: 15px;
  cursor: pointer;
}

.hy li.active {
  color: #399dfb;
  border: 1px solid #399dfb;
}

.ritem {
  border-radius: 20px;
  height: 120px;
  background-color: #fff;
  margin: 0 10px 22px;
  position: relative;
}

.r01 {
  background-image: url(../../assets/r01.jpg);
  background-repeat: no-repeat;
  background-position-x: 90%;
  background-position-y: center;
}

.r02 {
  background-image: url(../../assets/r02.jpg);
  background-repeat: no-repeat;
  background-position-x: 90%;
  background-position-y: center;
}

.r03 {
  background-image: url(../../assets/r03.png);
  background-repeat: no-repeat;
  background-position-x: 90%;
  background-position-y: center;
}

.ritem .title {
  position: absolute;
  left: 48px;
  top: 24px;
  width: 150px;
  font-size: 18px;
  text-align: center;
  font-weight: bold;
}

.ritem .numdiv {
  position: absolute;
  left: 48px;
  top: 52px;
  width: 150px;
  font-size: 18px;
  text-align: center;
  font-weight: bold;
  padding-top: 10px;
}

.ritem .numdiv span {
  font-size: 30px;
}

.r01 .numdiv {
  color: #7f0af3;
}

.r02 .numdiv {
  color: #3082ff;
}

.r03 .numdiv {
  color: #ff7f20;
}

.r04 {
  height: 250px;
}

.sellorder {
  height: 250px;
  position: absolute;
  left: 48px;
  top: 52px;
  width: 75%;
}
</style>
