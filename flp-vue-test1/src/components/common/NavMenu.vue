<template>
  <el-menu
    :default-active="'/index'"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="red"
    style="min-width: 1300px">
    <i class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>

    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>
   <!-- <a href="#nowhere" style="color: #222;float: right;padding: 20px;">更多功能</a>-->
   <!-- <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>-->
    <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">让生活更效率(*^▽^*)</span>
  </el-menu>
</template>

<script>
  export default {
    name: 'NavMenu',
    data () {
      return {
        navList: [
          {name: '/index', navItem: '首页'},
          {name: '/timePlan',navItem: '时间管理'},
          /*{name: '/jotter', navItem: '笔记本'},*/
         /* {name: '/library', navItem: '图书馆'},*/
          {name: '/admin', navItem: '系统管理'}
        ]
      }
    },
    methods: {
      // logout () {
      //   var _this = this
      //   this.$axios.get('/logout').then(resp => {
      //     if (resp.data.code === 200) {
      //       // 前后端状态保持一致
      //       _this.$store.commit('logout')
      //       _this.$router.replace('/login')
      //     }
      //   }).catch(() => {
      //     this.$message({
      //       type: 'info',
      //       message: '登出失败'
      //     })
      //   })
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
  a{
    text-decoration: none;
  }

  span {
    pointer-events: none;
  }

  .el-icon-switch-button {
    cursor: pointer;
    outline:0;
  }

</style>

