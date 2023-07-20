<template>
    <div>
      <!-- <i class="el-icon-potato-strips"  @click="dialogFormVisible = true"></i> -->
      <el-dialog
        title="添加书评"
        :visible.sync="dialogFormVisible"
        @close="clear">
        <el-form v-model="commentform" style="text-align: left" ref="dataForm">
          <el-form-item label="ISBN" :label-width="formLabelWidth">
            <el-input v-model="commentform.isbn" autocomplete="off" placeholder="可在图书馆中找到"></el-input>
          </el-form-item>
          <el-form-item label="评论" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="commentform.review" autocomplete="off"></el-input>
          </el-form-item>
          <!-- <el-form-item prop="id" style="height: 0">
            <el-input type="hidden" v-model="form.id" autocomplete="off"></el-input>
          </el-form-item> -->
          <el-form-item label="推荐等级" :label-width="formLabelWidth">
            <el-slider
              v-model="commentform.level"
              max="5"
              step="0.1"
              show-input>
            </el-slider>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="onSubmit">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
    export default {
      name: 'EditForm',
      data () {
        return {
          dialogFormVisible: false,
          commentform: {
            isbn: '',
            review: '',
            level: null
          },
          formLabelWidth: '120px'
        }
      },
      methods: {
        clear () {
          this.commentform = {
            isbn: '',
            review: '',
          }
        },
        onSubmit () {
          this.$axios
            .post('/add/comment', {
              uid: JSON.parse(sessionStorage.getItem("uid")),
              isbn: this.commentform.isbn,
              review: this.commentform.review,
              level: this.commentform.level
            }).then(resp => {
            if (resp && resp.data.code === 200) {
              this.dialogFormVisible = false
              // this.$emit('onSubmit')
            }
          })
        }
      }
    }
  </script>
  
  <style scoped>
  </style>
