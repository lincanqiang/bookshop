<template>
    <div>
        <el-form :model="user"
                 ref="user"
                 rules="rules"
                 label-position="left"
                 label-width="0px"
                 class="page">
            <h3 class="title">用户注册</h3>
            <el-form-item prop="username">
                <el-input type="text"
                          v-model="user.name"
                          auto-complete="off"
                          placeholder="请输入用户名"
                />
            pel-input type="password"
                          v-model="user.password"
                          auto-complete="off"
                          placeholder="请输入密码"
                />
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password"
                          v-model="user.repeatPassword"
                          auto-complete="off"
                          placeholder="密码确认"
                />
            </el-form-item>
            <el-checkbox
                    v-model="checked"
            >记住密码</el-checkbox>
            <el-form-item style="width:100%;">
                <el-button type="primary" style="width:100%;" @click="handleRegistry">注册</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name:"Register",
        data(){
            const checkName = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('用户名不能为空'));
                } else{
                    callback();
                }
            };
            const validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.user.repeatPassword !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            const validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.user.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                uri:"register",
                user: {
                    name: '',
                    password: '',
                    repeatPassword:'',
                },
                checked: false,
                rules:{
                    name:[
                        {validator:checkName,trigger:'blur'}
                    ],
                    password:[
                        {validator:validatePass,trigger:'blur'}
                    ],
                    repeatPassword:[
                        {validator:validatePass2,trigger:'blur'}
                    ]
                }
            }
        },
        methods: {
            handleRegistry() {
                this.$refs['user'].validate((valid) => {
                    if (valid) {
                        this.axios.post(this.uri,{
                            name:this.user.name,
                            password:this.user.password
                        })
                            .then(response =>{
                                if(response.data.code === 200){
                                    this.$message('注册成功！');
                                    this.$router.push('/login');
                                }else{
                                    this.$alert('用户名已被占用，请重新注册','info',{
                                        confirmButtonText:'ok'
                                    })
                                    this.$refs['form'].resetFields();
                                }
                            })
                    } else {
                        return false;
                    }
                });
            }
        }
    };
</script>

<style scoped>
    .page {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        margin: 180px auto;
        width: 350px;
        padding: 35px 35px 15px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    label.el-checkbox {
        margin: 0px 0px 15px;
        text-align: left;
    }
</style>