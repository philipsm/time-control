<template>
  <el-collapse v-model="activeName" class="categories" accordion>
    <el-collapse-item :title="item.title" :name="item.timePlanId"
                      v-for="item in timePlanList" :key="item.timePlanId"
                      @click.native="selectPlanById(item.timePlanId)">
      <!--<el-button icon="el-icon-delete" style="float:right" circle></el-button>-->
      <div style="cursor:pointer;" @dblclick="showDetailDrawer(item.timePlanId)">
          {{item.planDescribe}}
      </div>
    </el-collapse-item>
    <el-button plain style="width: 100%" icon="el-icon-plus"  @click.native="openDrawer()">添加</el-button>
    <!-- 抽屉 -->
    <el-drawer
      title="添加计划!"
      :before-close="handleClose"
      :visible.sync="dialog"
      :with-header ="false"
      :modal="false"
      direction="ltr"
      custom-class="demo-drawer"
      ref="drawer"
      class="time-plan-drawer"
    >
      <div >
        <el-form :model="form" style="margin-top: 15%">
          <el-form-item label="计划名称" :label-width="formLabelWidth">
            <el-input v-model="form.title" autocomplete="off" class="time-plan-drawer" clearable></el-input>
          </el-form-item>
          <el-form-item label="计划描述" :label-width="formLabelWidth">
            <el-input type="textarea" :rows="4" class="time-plan-drawer" v-model="form.planDescribe" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="计划类别" :label-width="formLabelWidth">
            <el-select v-model="form.planType" placeholder="请选择类别" class="time-plan-drawer">
              <el-option label="固定日期" :value=1></el-option>
              <el-option label="每日循环" :value=2></el-option>
              <el-option label="工作日循环" :value=3></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div class="time-plan-drawer__footer">
          <el-button @click="cancelForm">取 消</el-button>
          <el-button :style="{ display: visibleDelete }" @click="deletePlan(form.timePlanId)">删 除</el-button>
          <el-button type="primary" @click="$refs.drawer.closeDrawer()" :loading="loading">{{ loading ? '提交中 ...' : '保 存' }}</el-button>
        </div>
      </div>
    </el-drawer>
  </el-collapse>

</template>

<script>
  export default {
    name: 'TimeSideMenu',
    iniect:['reload'],  //注入reload方法
    data () {
      return {
        activeName: '1',
        timePlanList: [],
        selectPlanId: '',
        data: [
          {
            timePlanId: 1,
            title: "运动计划",
            planDescribe: "每日在家里面进行定量运动",
            planType: '2'
          },
          {
            timePlanId: 2,
            title: "日常计划",
            planDescribe: "每天玩游戏，学习，运动时间要按计划做到平衡",
            planType: '1'
          }
        ],
        /*抽屉*/
        dialog: false,
        loading: false,
        /*抽屉的删除按钮隐藏控制*/
        visibleDelete: 'none',
        form: {
          timePlanId: 0,
          title: '',
          planDescribe: '',
          planType: ''
        },
        formLabelWidth: '80px',
        timer: null,
      }
    },
    mounted: function(){
      this.loadPlans()
    },
    methods: {

      loadPlans(){
        var _this = this
        //查询后台列表数据
        this.$axios.post('/timeplan/getTimePlanList',{id: this.$store.state.user.id}).then(resp => {
          if (resp && resp.status === 200) {
            _this.timePlanList = resp.data
          }
        })
        _this.selectPlanId = this.data[0].timePlanId
      },
      /**/
      selectPlanById(id){
        //var _this = this
        this.selectPlanId = id
        console.log("要传的ID是："+id)
        this.$emit('getSelectPlanId')
      },

      /*删除计划*/
      deletePlan(id){
        var delete_this = this
        this.$confirm('是否删除该计划?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .post('/timeplan/deleteByTimePlanId', {timePlanId: id}).then(resp => {
              if (resp && resp.status === 200) {
                this.$message({
                  message: '删除成功',
                  type: 'success'
                });
                //刷新页面
                delete_this.timePlanList = delete_this.timePlanList.filter(
                  function (item) {
                    if (item.timePlanId != id) {
                      return item
                    }
                })
                delete_this.dialog=false
                delete_this.clearForm()
              }
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },

      /*抽屉*/
      handleClose(done) {
        if (this.loading) {
          return;
        }
        var _this = this
        this.$confirm('确定要提交表单吗？')
          .then(_ => {
            this.loading = true;
            this.timer = setTimeout(() => {
              done();
              // 动画关闭需要一定的时间
              setTimeout(() => {
                //保存计划
                this.$axios
                  .post('/timeplan/addTimePlan', {
                    title: this.form.title,
                    planDescribe: this.form.planDescribe,
                    planType: this.form.planType
                  })
                  .then(resp => {
                    if (resp.status === 200) {
                      this.loading = false;
                      this.$message({
                        message: '保存成功',
                        type: 'success'
                      });
                      _this.dialog=false
                      _this.timePlanList.push(resp.data)
                     this.clearForm()
                      //重新加载页面
                      //debugger
                      //this.reload()

                    } else {
                      this.$message({
                        message: '保存失败',
                        type: 'error'
                      });
                    }
                  })
                  .catch(failResponse => {})

              }, 400);
            }, 1000);

          })
          .catch(_ => {});
      },

      //清空抽屉表单数据
      clearForm(){
        this.form = {
          timePlanId: 0,
          title: '',
          planDescribe: '',
          planType: ''
        }
      },

      //关闭抽屉
      cancelForm() {
        this.visibleDelete = 'none'
        this.loading = false;
        this.dialog = false;
        clearTimeout(this.timer);
      },

      //打开抽屉
      openDrawer(){
        this.clearForm()
        this.dialog = true
      },

      //双击进入抽屉详情
      showDetailDrawer(id){
        this.clearForm()
        this.visibleDelete = ''
        this.dialog = true
        //查询数据
        var selectPlan = {}
        this.timePlanList.forEach(item => {
          if (item.timePlanId == id){
            selectPlan = item
          }
        })
        //填充抽屉数据
        this.form.timePlanId = selectPlan.timePlanId
        this.form.planDescribe = selectPlan.planDescribe
        this.form.title = selectPlan.title
        this.form.planType = selectPlan.planType
      }

    }
  }
</script>

<style scoped>
  .categories {
    position: fixed;
    /*margin-left: 30%;*/
    left: 10%;
    top: 100px;
    width: 15%;
  }

  .time-plan-drawer {
    width: 70%
  }

  .drawer-form {
    width: 75%;
  }
</style>

