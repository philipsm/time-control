<template>
  <el-card class="admin-header">
    <a href="/index">
      <img src="../../assets/flp/admin/admin_return.jpg" alt="" width="55px" style="float: left;margin-top: -5px;">
    </a>
    <span style="font-size: 32px;font-weight: bold;position:absolute;left: 100px">系统管理</span>
    <i class="el-icon-switch-button" v-on:click="logout" style="font-size: 40px;float: right"></i>
  </el-card>
</template>

<script>
  import {createRouter} from '../../router'

  export default {
    name: 'Header',
    methods: {
      // logout () {
      //   var _this = this
      //   this.$axios.get('/logout').then(resp => {
      //     if (resp.data.code === 200) {
      //       _this.$store.commit('logout')
      //       _this.$router.replace('/index')
      //       // 清空路由，防止路由重复加载
      //       const newRouter = createRouter()
      //       _this.$router.matcher = newRouter.matcher
      //     }
      //   }).catch(failResponse => {})
      // }

      logout () {
        var _this = this
        this.$confirm('是否登出系统?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.get('/logout').then(resp => {
            if (resp.data.code === 200) {
              // 前后端状态保持一致
              _this.$store.commit('logout')
              _this.$router.replace('/login')
            }
          }).catch(() => {
            this.$message({
              type: 'info',
              message: '登出失败'
            })
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消登出'
          });
        });
      }

    }
  }
</script>

<style scoped>
  .admin-header {
    height: 80px;
    opacity: 0.85;
    line-height: 40px;
    min-width: 900px;
  }
  .el-icon-switch-button {
    cursor: pointer;
    outline:0;
  }
</style>
