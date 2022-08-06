// 分页混入

export default {
  data() {
    return {
      createDate: [],
      totalData: {
        totalRecord: 0,
        records: []
      },
      queryFrom: {
        page: 1,
        size: 10
      },
      multipleSelection: [],
      ableEdit: true,
      ableDelete: true,
      dialogForm: {
        status: 1
      },
      dialogVisible: false,
      dialogType: 0,
      dialogTitle: ''

    }
  }, created() {
    this.fetchData()
  }, methods: {
    // 获取数据
    fetchData() {
      delete this.queryFrom.startDate
      delete this.queryFrom.endDate
      if (this.createDate && this.createDate.length === 2) {
        this.queryFrom.createDate = this.createDate[0]
        this.queryFrom.endDate = this.createDate[1]
      }
      if (!(this.fetchDataHook && this.fetchDataHook instanceof Function)) {
        this.$message.error('fetchDataHook不存在！')
        return
      }
      const invoke = this.fetchDataHook()
      invoke(this.queryFrom).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.totalData = data
        }
      }).catch(error => {
        console.log(error)
      })
    },
    // 搜索时发生的行为
    submitSearch() {
      this.queryFrom.page = 1
      this.fetchData()
    },
    // 当每页的数量发生变化
    handleSizeChange(val) {
      this.queryFrom.size = val
    },
    // 当当前页数发生变化
    handleCurrentPageChange(val) {
      this.queryFrom.page = val
      this.fetchData()
    },
    // 当选择框发生变化
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.ableEdit = this.multipleSelection.length !== 1
      this.ableDelete = this.multipleSelection.length === 0
    },
    // 打开dialog
    openDialog(type) {
      this.dialogType = type
      if (type === 2) {
        this.dialogTitle = '修改'
        this.dialogForm = this.multipleSelection[0]
        this.dialogVisible = true
      } else {
        this.dialogTitle = '新增'
        this.dialogForm = { status: 1 }
        this.dialogVisible = true
      }
    },
    // 提交dialog表单
    submitForm() {
      this.$refs['dialogForm'].validate((valid) => {
        if (valid) {
          if (this.dialogType === 1) {
            this.submitNew()
          } else if (this.dialogType === 2) {
            this.submitEdit()
          }
          this.dialogVisible = false
          setTimeout(() => {
            this.fetchData()
          }, 1000)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 新增
    submitNew() {
      if (!(this.addDataHooK && this.addDataHooK instanceof Function)) {
        this.$message.error('addDataHook不存在！')
        return
      }
      const invoke = this.addDataHooK()
      invoke(this.dialogForm).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.$message({
            message: message,
            type: 'success'
          })
        } else {
          this.$message({
            message: message || '操作失败！',
            type: 'danger'
          })
        }
      }).catch(error => {
        console.log(error)
      })
    },
    // 修改
    submitEdit() {
      if (!(this.editDataHook && this.editDataHook instanceof Function)) {
        this.$message.error('editDataHook不存在！')
        return
      }
      const invoke = this.editDataHook()
      invoke(this.dialogForm).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.$message({
            message: message,
            type: 'success'
          })
        } else {
          this.$message({
            message: message || '操作失败！',
            type: 'danger'
          })
        }
      }).catch(error => {
        console.log(error)
      })
    },
    // 删除
    submitDelete() {
      if (!(this.delDataHook && this.delDataHook instanceof Function)) {
        this.$message.error('delDataHook不存在！')
        return
      }
      const invoke = this.delDataHook()
      invoke(this.multipleSelection).then(response => {
        const { code, message, data } = response
        if (code === 0) {
          this.$message({
            message: message,
            type: 'success'
          })
        } else {
          this.$message({
            message: message || '操作失败！',
            type: 'danger'
          })
        }
      }).catch(error => {
        console.log(error)
      })
    },
    // 关闭表单
    closeDialog() {
      this.resetForm()
      this.dialogVisible = false
    },
    // 重置表单
    resetForm() {
      this.$refs['dialogForm'].resetFields()
    }
  }
}
