<template>
  <el-container>
    <!--<el-container>-->
      <el-aside width="25%">
        <!--<SideMenu  ref="sideMenu" :childSelectId="modifySelectId"></SideMenu>-->
        <SideMenu  ref="sideMenu" @getSelectPlanId="modifySelectId"></SideMenu>
      </el-aside>

      <el-main class="plan-area">
        <!--<TimePlanDetail :inputId="leftChildId"></TimePlanDetail>-->
        <TimePlanDetail ref="timePlanDetail" :inputId="leftChildId"></TimePlanDetail>
      </el-main>
   <!-- </el-container>-->
  </el-container>
</template>

<script>
  import SideMenu from './SideMenu'
  import TimePlanDetail from './TimePlanDetail'

    export default {
        name: "TimePlan",
        components: {SideMenu,TimePlanDetail},
        data(){

          return{
            selectPlanId: 0,
            leftChildId: 0
          }
        },

        methods: {
          modifySelectId() {
            var planId = this.$refs.sideMenu.selectPlanId
            console.log("拿到的ID是："+planId)
            this.leftChildId = planId
            this.$refs.timePlanDetail.getPlanDetailListById(planId)
          }

        }

    }
</script>

<style scoped>
  .plan-area {
    width: 70%;
    margin-left: auto;
    margin-right: auto;
  }
</style>
