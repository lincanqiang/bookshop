<template>
    <div>
        <el-form :model="user"
                 ref="user"
                 rules="rules"
                 label-position="left"
                 label-width="0px"
                 class="page">
            <h3 class="title">用户登录</h3>
            <el-form-item prop="username">
                <el-input type="text"
                          v-model="user.name"
                          auto-complete="off"
                          placeholder="请输入用户名"
                />
            </el-form-item>
            <el-form-item prop="password">
                <el-input type="password"
                          v-model="user.password"
                          auto-complete="off"
                          placeholder="请输入密码"
                />
            </el-form-item>
            <el-checkbox
                    v-model="checked"
            >记住密码</el-checkbox>
            <el-form-item style="width:100%;">
                <el-button type="primary" style="width:100%;" @click="handleLogin">登录</el-button>
            </el-form-item>
            <el-form-item><el-button type="text" @click="$router.push('/register')">还没有账号？点击去注册</el-button> </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name:"Login",
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
            return {
                uri:"login",
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
                    ]
                }
            }
        },
        methods: {
            handleLogin() {
                this.$refs['user'].validate((valid) => {
                    if (valid) {
                        this.axios.post(this.uri, {
                            name:this.user.name,
                            password:this.user.password
                        })
                            .then(response =>{
                                if(response.data.code === 200){
                                    sessionStorage.setItem('user',this.user.name);
                                    this.$message('登录成功！');
                                    this.$router.push('/');
                                }else{
                                    this.$alert('账号或密码错误','info',{
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