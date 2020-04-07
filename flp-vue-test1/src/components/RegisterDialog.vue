<template>
  <el-dialog title="注册" :visible.sync="dialogFormVisible" @close="resetForm('ruleForm')" :modal-append-to-body="false" width="25%">
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm" >
      <el-form-item label="登陆名" prop="username" >
        <!--<el-col :span="18">-->
          <el-input v-model.number="ruleForm.username" ></el-input>
        <!--</el-col>-->
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <!--<el-col :span="18">-->
          <el-input type="password" v-model="ruleForm.password" autocomplete="off" ></el-input>
        <!--</el-col>-->
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <!--<el-col :span="18">-->
          <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" ></el-input>
        <!--</el-col>-->
      </el-form-item>
      <el-form-item label="真实姓名" prop="name">
        <!--<el-col :span="18">-->
        <el-input v-model.number="ruleForm.name" ></el-input>
        <!--</el-col>-->
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <!--<el-col :span="18">-->
          <el-input v-model.number="ruleForm.phone" ></el-input>
        <!--</el-col>-->
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <!--<el-col :span="18">-->
          <el-input v-model.number="ruleForm.email"></el-input>
        <!--</el-col>-->
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button @click="resetForm('ruleForm')">重 置</el-button>
      <el-button type="primary" @click="register()">确 定</el-button>
    </div>
  </el-dialog>

</template>

<script>

  export default {
    name: 'RegisterDialog',
    data() {
      var validateCheckPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
        } else if (value !== this.ruleForm.password) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        dialogFormVisible: false,
        ruleForm: {
          password: '',
          checkPass: '',
          username: '',
          name: '',
          phone: '',
          email: ''

        },
        rules: {
          username: [
            { required: true, message: '请输入登录名', trigger: 'blur' },
            { pattern:/^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线'}],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            {pattern: /^(\w){6,20}$/, message: '只能输入6-20个字母、数字、下划线'}],
          checkPass: [
            { required: true, message: '请再次输入密码', trigger: 'blur' },
            { validator: validateCheckPass} ],
          name: [
            { required: true, message: '请输入真实姓名', trigger: 'blur' },
            { pattern:/^[\u2E80-\u9FFF]+$/, message: '只能输入汉字'}],
          phone: [
            { required: true, message: '请输入手机号码', trigger: 'blur' },
            { pattern:/^1[12345789]\d{9}$/, message: '请输入正确的手机号码'}],
          email: [
            { required: true, message: '请填写E-mail', trigger: ['blur','change'] },
            { type: 'email', message: '请输入正确的E-mail', trigger: ['blur','change'] }],
        }
      };
    },
    methods: {
      register () {
        var _this = this
        this.$axios
          .post('/register', {
            username: this.ruleForm.username,
            password: this.ruleForm.password,
            name: this.ruleForm.name,
            phone: this.ruleForm.phone,
            email: this.ruleForm.email
          })
          .then(resp => {
            if (resp.data.code === 200) {
              this.$alert('注册成功', '提示', {
                confirmButtonText: '确定'
              })
              _this.dialogFormVisible=false
              _this.$router.replace('/login')
            } else {
              this.$alert(resp.data.message, '提示', {
                confirmButtonText: '确定'
              })
            }
          })
          .catch(failResponse => {})

      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  };
</script>

<style scoped>
  .demo-ruleForm{
    margin-left: 5%;
    margin-right: 5%;
  }
</style>
