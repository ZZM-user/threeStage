<template>
  <div id="main">
    <el-row :gutter="10">
      <el-col v-for="item in this.totalData.records" :span="6">
        <el-card :class="borderColor(item)" shadow="hover">
          <div slot="header" class="clearfix">
            <span>{{ item.room }}</span>
            <el-button style="float: right; padding: 0 0" type="text" @click="openDialog(item)">一键入住
            </el-button>
          </div>

          <div class="text item">
            {{ '房间单价：' + item.priceOfDay + '$ / 天' }}
          </div>
          <div class="text item">
            {{ '入住情况：' + item.hasEmployee + ' / ' + item.totalPeople }}
          </div>
        </el-card>
      </el-col>
    </el-row>
    <!--dialog-->
    <el-dialog :visible.sync="dialogVisible" title="员工入住" width="30%">
      <el-form ref="dialogForm" :inline="true" :model="dialogForm" :rules="rules" class="demo-form-inline">
        <el-form-item label="入住员工" prop="eid">
          <el-select v-model="dialogForm.eid"
                     :loading="loading"
                     :remote-method="remoteMethod" clearable filterable
                     placeholder="请选择" remote
                     reserve-keyword>
            <el-option
              v-for="item in employeeList"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入住日期" prop="enterDate">
          <el-date-picker
            v-model="dialogForm.enterDate"
            placeholder="选择入住日期"
            type="date">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="退房日期">
          <el-date-picker
            v-model="dialogForm.leaveDate"
            placeholder="选择退房日期"
            type="date">
          </el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <!--    分页-->
    <el-pagination
      :current-page="this.queryFrom.page"
      :page-size="this.queryFrom.size"
      :page-sizes="[12, 24, 36, 48, 60]"
      :total="this.totalData.totalRecord"
      layout="total, sizes, prev, pager, next, jumper"
      @size-change="handleSizeChange"
      @current-change="handleCurrentPageChange">
    </el-pagination>
  </div>
</template>

<script>
import PageMixin from "@/mixin/PageMixin";
import {fetchRoomData} from "@/api/room";
import {fetchEmployeeData} from "@/api/employee";
import {enterRecord} from "@/api/record";

export default {
  name: "index",
  mixins: [PageMixin],
  data() {
    return {
      queryFrom: {
        page: 1,
        size: 16
      },
      dialogForm: {
        eid: '',
        rid: '',
        enterDate: '',
        leaveDate: ''
      },
      loading: false,
      employeeList: undefined,
      rules: {
        eid: [
          {required: true, message: '请选择员工', trigger: 'blur'},
        ],
        enterDate: [
          {required: true, message: "请选择入住日期", trigger: 'blur'}
        ]
      }
    }
  },
  computed: {
    // 计算卡片边框样式
    borderColor() {
      return function (item) {
        if (item.totalPeople - item.hasEmployee === 0) {
          return "box-card not-empty"
        }
        return "box-card has-empty"
      }
    }
  },
  methods: {
    fetchDataHook() {
      return fetchRoomData
    },
    // 远程搜索员工
    remoteMethod(query) {
      if (query !== '') {
        this.loading = true;
        fetchEmployeeData({page: 1, size: 10, name: query, hasRoom: 0}).then(resp => {
          this.loading = false;
          this.employeeList = resp.data.records.map(item => {
            return {value: `${item.id}`, label: `${item.name}`};
          });
        })
      } else {
        this.options = [];
      }
    },
    // 打开dialog
    openDialog(item) {
      if (item.totalPeople === item.hasEmployee) {
        this.$message({
          message: '该房间已经没有空位！',
          type: 'warning'
        });
        return
      }
      this.dialogForm.rid = item.id
      this.dialogVisible = true
      this.dialogTitle = "员工入住"
    },
    //提交
    submitForm() {
      this.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          // this.dialogForm.eid = parseInt(this.dialogForm.eid)
          enterRecord(this.dialogForm).then(resp => {
            if (resp.code === 0) {
              this.$message({
                message: resp.message,
                type: 'success'
              });
            }
            this.fetchData()
            this.dialogVisible = false
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm() {
      if (this.$refs.form !== undefined) {
        this.$refs['dialogForm'].resetFields();
      }
    }
  }
}
</script>

<style>
#main {
  padding: 1%;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix {
  font-weight: 600;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 300px;
  margin: 3% 0;
  background: #f8f8f8;
}

.has-empty {
  border-color: #2ecc71;
}

.not-empty {
  border-color: red;
}
</style>
