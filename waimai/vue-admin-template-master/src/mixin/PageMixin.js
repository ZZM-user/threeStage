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
      ids: [],
      ableEdit: true,
      ableDelete: true,
      dialogForm: {},
      dialogVisible: false,
      dialogType: 0,
      dialogTitle: ''

    }
  }, created() {
    this.fetchData()
    this.initFormValue()
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
      this.ids = val.map(p => p.id)
      this.ableEdit = this.multipleSelection.length !== 1
      this.ableDelete = this.multipleSelection.length === 0
    },
    // 打开dialog
    openDialog(type, row) {
      this.dialogType = type
      this.resetForm()
      if (type === 2) {
        // 如果在调用编辑前，需要请求数据
        if (this.beforeEditDataHook && this.beforeEditDataHook instanceof Function) {
          this.beforeEditDataHook(row)
        }
        this.dialogTitle = '修改'
        this.dialogVisible = true
      } else if (type === 1) {
        this.dialogTitle = '新增'
        this.dialogForm = {}
        this.dialogVisible = true
      } else {
        if (this.beforeEditDataHook && this.beforeEditDataHook instanceof Function) {
          this.beforeEditDataHook(row)
        }
        this.dialogTitle = '详情'
        this.dialogVisible = true
      }
      this.fetchData()
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
        this.dialogVisible = false
        this.fetchData()
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
        this.fetchData()
      }).catch(error => {
        console.log(error)
      })
    },
    // 删除
    submitDelete(row) {
      if (!(this.delDataHook && this.delDataHook instanceof Function)) {
        this.$message.error('delDataHook不存在！')
        return
      }
      const ids = row.id ? Array.of(row.id) : this.ids
      const invoke = this.delDataHook()
      invoke(ids).then(response => {
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
        this.fetchData()
      }).catch(error => {
        console.log(error)
      })
    },
    // 改变状态
    submitChangeStatus(row) {
      if (!(this.changeStatusHook && this.changeStatusHook instanceof Function)) {
        this.$message.error('changeStatusHook不存在！')
        return
      }
      const invoke = this.changeStatusHook()
      invoke({ id: row.id }).then(response => {
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
        this.fetchData()
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
      if (this.$refs['dialogForm']) {
        this.$refs['dialogForm'].resetFields()
        this.$refs['dialogForm'].clearValidate()
      }
      // 清空表单校验，避免再次进来会出现上次校验的记录
      if (this.avatar) {
        this.avatar = ''
      }
    },
    // 初始化表单值
    initFormValue() {
      this.dialogForm = {
        status: 1
      }
    },
    // 上传头像之前
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/jpg' || file.type === 'image/gif'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传封面图片只能是 JPG,PNG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传封面图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
