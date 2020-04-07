<template>
  <div class="block" style="width: 60%" @dblclick="showAddPlanDetail()">
    <TimePlanNodata v-if="showNoDataPage" :icon="require('@/assets/timePlan-empty.png')" text="暂无计划"
                    style="cursor: pointer;" ></TimePlanNodata>
    <el-timeline  >
      <div style="cursor:pointer;min-height: 300px;" >
        <el-timeline-item :timestamp="item.timeSlice" placement="top"  v-for="item in toShowDataList" :key="item.timePlanDetailId" style="text-align: left">
          <el-card @dblclick.native.stop="modifyPlanDetail(item.timePlanDetailId)">
            <h4>{{item.planContent}}</h4>
            <p>{{item.detailRemarks}}</p>
            <i class="el-icon-delete" @click="deletePlanDetail(item.timePlanDetailId)"></i>
          </el-card>

        </el-timeline-item>
      </div>
    </el-timeline>
    <!--添加计划详情弹窗-->
    <el-dialog title="计划详情" :visible.sync="dialogFormVisible" @close="resetForm('ruleForm')" :modal-append-to-body="false" width="25%">
      <el-form :model="ruleForm"  status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" >
        <el-form-item label="开始时间"  prop="startTime">
          <el-time-picker v-model="ruleForm.startTime"  placeholder="任意时间点"  class="time-plan-drawer"
                          format="HH:mm" value-format="HH:mm"></el-time-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-time-picker v-model="ruleForm.endTime" autocomplete="off"  placeholder="任意时间点" class="time-plan-drawer"
                          format="HH:mm" value-format="HH:mm" ></el-time-picker>
        </el-form-item>
        <el-form-item label="计划内容" prop="planContent">
          <el-input type="textarea" :rows="2" v-model="ruleForm.planContent" autocomplete="off"
                    class="time-plan-drawer" ></el-input>
        </el-form-item>
        <el-form-item label="备注" >
          <el-input type="textarea" :rows="4" v-model="ruleForm.detailRemarks" autocomplete="off"
                    class="time-plan-drawer" ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import TimePlanNodata from '../nodata/timePlan-nodata.vue'
  export default {
    name: 'TimePlanDetail',
    components: {TimePlanNodata},
    props: {
      inputId: Number
    },
    data() {
      return {
        showNoDataPage: false,
        toShowDataList: [],
        AllDataList: [],
        toShowData: {},
        parentId: 0,
        clickFlag: 0,
        /*计划详情弹窗*/
        dialogFormVisible: false,
        ruleForm: {
          timePlanDetailId: 0,
          timePlanId: 0,
          startTime: '',
          endTime: '',
          planContent: '',
          detailRemarks: ''
        },
        rules: {
          startTime: [
            { required: true, message: '请输入开始时间', trigger: 'blur' },],
          endTime: [
            { required: true, message: '请输入结束时间', trigger: 'blur' },],
          planContent: [
            { required: true, message: '请输入计划内容', trigger: 'blur' },]
        }
      }
    },
    mounted: function () {
      console.log("初始化的时候从父页面得到的ID："+this.inputId)
      //初始化
      this.initPlanData(this.inputId)
    },
    // watch: {
    //   inputId(val){debugger
    //     console.log("监听接收到的是："+val)
    //     this.getPlanDetailById(val)
    //   },
    //   immediate: true
    // },
    methods: {
      //打开卡片页面
      showAddPlanDetail(){
        console.log("增加")
        //清空表单
        this.clearForm()
       this.dialogFormVisible = true
      },
      /*增加计划详情*/
      submitForm(){
        var _this = this
        /*var data = this.ruleForm
        data["timePlanId"] = this.parentId*/
        var data = {
          timePlanDetailId: this.ruleForm.timePlanDetailId,
          timePlanId: this.parentId,
          startTime: this.ruleForm.startTime,
          endTime: this.ruleForm.endTime,
          planContent: this.ruleForm.planContent,
          detailRemarks: this.ruleForm.detailRemarks
        }
        console.log(JSON.stringify(data))
        //前端校验
        if(!data.startTime||!data.endTime||!data.planContent){
          this.$message({type: "error",message: "保存失败"})
          return
        }
        // qs.stringify({‘objId’:id}),{ headers: { ‘Content-Type’: ‘application/x-www-form-urlencoded’, } }
        this.$axios.post('/timeplan/savePlanDetail',data).then(resp => {
          if (resp && resp.status === 200) {
            console.log("----->"+JSON.stringify(resp.data))
            this.$message({type: 'success',message: '保存成功'})
            this.clearForm()
            this.dialogFormVisible = false
            //刷新页面
            if (data["timePlanDetailId"]==0){
              _this.AllDataList.push(resp.data)
            }else{

              this.AllDataList = this.AllDataList.filter(
                function (item) {
                  if (item.timePlanDetailId != resp.data.timePlanDetailId) {
                    return item
                  }
                })
              this.AllDataList.push(resp.data)
            }
            this.getPlanDetailListById(resp.data["timePlanId"])
          }
        })

      },
      //修改计划详情
      modifyPlanDetail(id){
        console.log("修改，ID="+id)
        this.dialogFormVisible = true
        this.getPlanDetailById(id)
        //alert("修改计划详情"+id)
      },
      //删除计划详情
      deletePlanDetail(id){
        this.$confirm('此操作将删除选中的计划, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
            this.$axios
              .post('/timeplan/deletePlanDetail', {timePlanDetailId: id}).then(resp => {
              if (resp && resp.status === 200) {
                this.$message({
                  type: 'success',
                  message: '已成功删除'
                })
              }
              //刷新页面
              this.AllDataList = this.AllDataList.filter(
                function (item) {
                  if (item.timePlanDetailId != id) {
                    return item
                  }
                })
              this.getPlanDetailListById(this.parentId)
            })
          }
        ).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
      },
      //卡片查询 (赋值)
      getPlanDetailById(detailId){
        console.log("赋值接收到的是："+detailId)
        var _this = this
        _this.toShowData = {}
        this.toShowDataList.forEach(item => {
          if (item.timePlanDetailId == detailId){
            _this.ruleForm.timePlanDetailId = item.timePlanDetailId
            _this.ruleForm.timePlanId = item.timePlanId
            _this.ruleForm.startTime = item.startTime
            _this.ruleForm.endTime = item.endTime
            _this.ruleForm.planContent = item.planContent
            _this.ruleForm.detailRemarks = item.detailRemarks
          }
        })
      },
      //列表查询 （赋值）
      getPlanDetailListById(id){
        var _this = this
        this.parentId = id
        _this.showNoDataPage = false
        _this.toShowDataList = []
        this.AllDataList.forEach(item => {
          if (item.timePlanId == id){
            _this.toShowDataList.push(item)
          }
        })
        _this.toShowDataList = this.sortList(_this.toShowDataList)
        //如果详情为空,展示无数据页面
        if (_this.toShowDataList.length == 0){
          _this.showNoDataPage = true
        }
      },

      /*列表按开始时间排序*/
      sortList(lists){
        return lists.sort((a, b) => {
          return a['startTime'].localeCompare(b['startTime'])     // grapheme为字母对应的属性名
        })
      },

      /*清空详情页*/
      clearForm(){
        this.ruleForm={
          timePlanDetailId: 0,
          timePlanId: 0,
          startTime: '',
          endTime: '',
          planContent: '',
          detailRemarks: ''
        }
      },
      /*重置表单*/
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      initPlanData(id){
        var _this = this;
        //查询后台列表数据
        this.$axios.get('/timeplan/getTimePlanDetailListByUser').then(resp=>{
          if (resp && resp.status === 200) {
            _this.AllDataList = resp.data
            console.log("查询列表时收到的ID："+id)
            var initId = 0
            if (this.parentId==0){
              initId = resp.data[0].timePlanId
            } else{
              initId = this.parentId
            }
            debugger
            this.getPlanDetailListById(initId)
          }
        })

/*      var data = [{
          timePlanDetailId: '1',
          timePlanId: '1',
          timeSlice: '08:30～09:30',
          startTime: '08:30',
          endTime: '10:30',
          planContent: '早起运动，煮饭，煮咖啡',
          detailRemarks: ''
        },
          {
            timePlanDetailId: '2',
            timePlanId: '1',
            timeSlice: '09:30～11:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '高效学习',
            detailRemarks: '尽量做非重复性的需要思考的事情'

          },
          {
            timePlanDetailId: '3',
            timePlanId: '1',
            timeSlice: '11:30～12:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '中午吃饭，休息娱乐一会',
            detailRemarks: '吃完饭不要立刻坐下，站一会活动下'

          },
          {
            timePlanDetailId: '4',
            timePlanId: '1',
            timeSlice: '13:00～14:00',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '午睡',
            detailRemarks: ''

          },
          {
            timePlanDetailId: '5',
            timePlanId: '1',
            timeSlice: '14:30～17:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '下午学习，学不进去的时候做一些运动休息下',
            detailRemarks: ''

          },
          {
            timePlanDetailId: '6',
            timePlanId: '1',
            timeSlice: '18:00～19:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '出去走圈，根据情况是否购物',
            detailRemarks: '购物的话记得带购物袋'

          },
          {
            timePlanDetailId: '7',
            timePlanId: '1',
            timeSlice: '20:00～23:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '打游戏，100个俯卧撑',
            detailRemarks: '俯卧撑分五组做，尽量标准'

          },
          //第二条数据
          {
            timePlanDetailId: '1',
            timePlanId: '2',
            timeSlice: '08:30～09:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '200个腹肌轮',
            detailRemarks: '分两组做'

          },
          {
            timePlanDetailId: '2',
            timePlanId: '2',
            timeSlice: '09:30～11:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '俯卧撑 100个',
            detailRemarks: '可以分几组做，确保动作标准，准备活动充足'

          },
          {
            timePlanDetailId: '3',
            timePlanId: '2',
            timeSlice: '11:30～12:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '午休时间',
            detailRemarks: '吃完饭不要立刻坐下，站一会活动下'

          },
          {
            timePlanDetailId: '4',
            timePlanId: '2',
            timeSlice: '13:00～14:00',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '午睡时间',
            detailRemarks: ''

          },
          {
            timePlanDetailId: '5',
            timePlanId: '2',
            timeSlice: '14:30～17:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '200个腹肌轮 100个卷腹',
            detailRemarks: ''

          },
          {
            timePlanDetailId: '6',
            timePlanId: '2',
            timeSlice: '18:00～19:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '出去跑步 5公里',
            detailRemarks: '购物的话记得带购物袋'

          },
          {
            timePlanDetailId: '7',
            timePlanId: '2',
            timeSlice: '20:00～23:30',
            startTime: '08:30',
            endTime: '10:30',
            planContent: '打游戏，100个俯卧撑',
            detailRemarks: '俯卧撑分五组做，尽量标准'

          }
        ]
*/

      },


    }

  }
</script>

<style scoped>
.time-plan-drawer {
  width: 80%;
}
.el-icon-delete {
  cursor: pointer;
  float: right;
}
</style>
