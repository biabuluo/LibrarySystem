<template>
  <div class="user" style="width: 1200px;font-weight: bolder">
    <div style="display:flex">
        <div class="addcard" style="font-size:larger;">
            <div style="margin:20px">用户管理列表</div>
            <div style="margin:20px;background-color: #DFCBE1;color: #618cac;border-radius: 30px;padding: 5px;box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);text-align: center;"  @click="dialogFormVisibletrue">
                <i class="el-icon-s-check" style="margin-top:5px;margin-bottom: 5px;font-weight: bolder;">增加</i>
            </div>
        </div>
        <div class="searchcard">
            <el-input
            @keyup.enter.native="searchClick"
            placeholder="search by uid or name.."
            prefix-icon="el-icon-search"
            size="small"
            style="width: 400px;"
            v-model="params">
            </el-input>
            <el-button size="small" type="primary" round="true" color="#DFCBE1" icon="el-icon-search" @click="load"></el-button>
        </div>
    </div>

    <div class="tablecard">
        <el-table :data="tableData" style="width: 100%;margin-bottom: 30px;" max-height="400">
            <el-table-column fixed prop="uid" label="编号" width="150"></el-table-column>
            <el-table-column prop="username" label="学号" width="120"></el-table-column>
            <el-table-column prop="name" label="姓名" width="120"></el-table-column>
            <el-table-column prop="nick" label="昵称" width="120"></el-table-column>
            <el-table-column prop="phone" label="联系方式" width="200"></el-table-column>
            <el-table-column prop="flag" label="是否为管理员" width="200"></el-table-column>
            <el-table-column label="操作" fixed="right" width="150">
              <template v-slot="scope">
                <el-button type="primary" size="small" round @click="dialogFormVisibletrue2(scope.row.uid)">编辑</el-button>
                <el-popconfirm
                    title="您确认删除这个用户吗？" style="margin-left: 10px"
                    @confirm="del(scope.row.username)">
                  <el-button slot="reference" size="small" round type="danger">删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
        </el-table>
    </div>

    <el-dialog title="新增用户" :visible.sync="dialogFormVisible" width="40%" style="margin-left: 10px" top="200px">
      <el-form ref="ruleForm" :model="form" :rules="rules" label-width="80px" :inline="true" style="width: 60%;margin-top: -20px">
        <el-form-item label="学号" width="100" prop="username" >
          <el-input v-model="form.username" placeholder="请输入学号"> </el-input>
        </el-form-item>
        <el-form-item label="密码" width="100">
          <el-input v-model="form.password" placeholder="请输入密码（默认123456）"> </el-input>
        </el-form-item>
        <el-form-item label="昵称" width="100" prop="name" >
          <el-input v-model="form.nick" placeholder="请输入昵称"> </el-input>
        </el-form-item>
        <el-form-item label="姓名" width="100" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名"> </el-input>
        </el-form-item>
        <el-form-item label="头像" width="100">
          <el-input v-model="form.img" placeholder="请输入头像url"> </el-input>
        </el-form-item>
        <el-form-item label="联系方式" width="100" prop="phone" >
          <el-input v-model="form.phone" placeholder="请输入联系方式"> </el-input>
        </el-form-item>
        <el-form-item label="管理权限" width="100">
          <!-- <el-input v-model="form.phone" placeholder="请输入联系方式"> </el-input> -->
          <el-radio v-model="form.isAdmin" label=0>普通用户</el-radio>
          <el-radio v-model="form.isAdmin" label=1>管理员</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="修改用户" :visible.sync="dialogFormVisible2" width="40%" style="margin-left: 10px" top="200px">
      <el-form ref="ruleForm2" :model="form2" :rules="rules" label-width="80px" :inline="true" style="width: 60%;margin-top: -20px">
        <el-form-item label="学号" width="100" prop="username" >
          <el-input v-model="form2.username" placeholder="请输入学号"> </el-input>
        </el-form-item>
        <el-form-item label="密码" width="100">
          <el-input v-model="form2.password" placeholder="请输入密码（默认123456）"> </el-input>
        </el-form-item>
        <el-form-item label="昵称" width="100" prop="name" >
          <el-input v-model="form2.nick" placeholder="请输入昵称"> </el-input>
        </el-form-item>
        <el-form-item label="姓名" width="100" prop="name">
          <el-input v-model="form2.name" placeholder="请输入姓名"> </el-input>
        </el-form-item>
        <el-form-item label="头像" width="100">
          <el-input v-model="form2.img" placeholder="请输入头像url"> </el-input>
        </el-form-item>
        <el-form-item label="联系方式" width="100" prop="phone" >
          <el-input v-model="form2.phone" placeholder="请输入联系方式"> </el-input>
        </el-form-item>
        <el-form-item label="管理权限" width="100">
          <!-- <el-input v-model="form.phone" placeholder="请输入联系方式"> </el-input> -->
          <el-radio v-model="form2.isAdmin" label=0>普通用户</el-radio>
          <el-radio v-model="form2.isAdmin" label=1>管理员</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'mana_user',
  data() {
    const checkPhone = (rule, value, callback) => {
      if (!/^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/.test(value)) {
        callback(new Error('请输入正确的手机号码'));
      }
      callback()
    };
    return {
      dialogFormVisible:false,
      dialogFormVisible2:false,
      tableData: [],
      flag:[],
      params:'',
      form:{},
      form2:{},
      rules: {
        username: [
          {
            required: true, 
            message: '请输入用户名', 
            trigger: 'blur'
          },
        ],
        name:[
          {
            required: true, 
            message: '字段未填！', 
            trigger: 'blur'
          },
        ],
        phone:[
          {
            validator: checkPhone, 
            trigger: 'blur', 
            message: '请输入正确的手机号', 
            required: true
          }
        ]
      }
    }
  },

  mounted:function() {  //页面加载的时候要做什么
    this.load();
    console.log('isadmin',this.tableData)
  },

  methods:{    //定义方法
    dialogFormVisibletrue(){
      this.dialogFormVisible=true
    },
    dialogFormVisibletrue2(uid){
      this.dialogFormVisible2=true
      //获取当前用户数据
      this.$axios.get('/'+uid+'/user_info').then(res=>{
        this.form2 = res.data
      })
    },
    save() {
          this.$axios.
            post('/adduser',this.form).then(res=>{
              if(res.data.code === 200){
                this.dialogFormVisible=false
                this.$message({
                  type:"info",
                  message: "添加成功"
                })   
              }else{
                this.$message({
                  type:"info",
                  message: "添加失败"
                }) 
              }
            })
      this.dialogFormVisible=false
      this.load()
    },

    update() {
          this.$axios.
            post('/adduser',this.form2).then(res=>{
              console.log('到这里了！')
              if(res.data.code === 200){
                this.dialogFormVisible2=false
                this.$message({
                  type:"info",
                  message: "添加成功"
                })   
              }else{
                this.$message({
                  type:"info",
                  message: "添加失败"
                }) 
              }
            })
      this.dialogFormVisible2=false
      this.load()
    },

    load(){
      var _this = this
      this.$axios.get('/user/search?keyword='+this.params).then(res => {
        console.log('user',res)
        for (var index = 0; index<res.data.length; index++) {
          if(res.data[index].isadmin==true){
           res.data[index]["flag"] = "true"
          } else{
            res.data[index]["flag"] = "false"
          }
        }
        if(res.status === 200){
          _this.tableData=res.data
        }
      })
    },
    del(id){
      console.log('id',id)
      this.$axios.post('/user/delete?keyword='+id).then(res =>{
        if(res.data.code===200){
          this.$message({
                  type:"info",
                  message: "删除成功"
                }) 
          this.load()
        }else{
          this.$message({
                  type:"info",
                  message: "删除失败"
          }) 
        }
      })
    }
  }
}
</script>

<style scoped>
.searchcard{
    margin: 20px;
    border-radius: 30px;
    box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 30px;
    width: 600px;
}
.addcard{
    margin: 20px;
    margin-right: 160px;
    border-radius: 30px;
    box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 30px;
}
.tablecard{
    margin: 20px;
    margin-right: 100px;
    border-radius: 30px;
    box-shadow:  0 2px 12px 0 rgba(0, 0, 0, 0.1);
    padding: 30px;
}
</style>