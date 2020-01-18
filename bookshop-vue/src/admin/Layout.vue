<template>
    <div>
        <el-container>
            <el-aside width="200px">
                <div>
                    <img src="../assets/logo.png" width="55px" height="55px"/>
                </div>
                <el-menu :default-active="1"
                         router
                >
                    <el-menu-item index="/admin">
                        <i class="el-icon-menu"/>
                        <span slot="title">分类管理</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/product">
                        <i class="el-icon-goods"/>
                        <span slot="title">商品管理</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/order">
                        <i class="el-icon-s-data"/>
                        <span slot="title">订单管理</span>
                    </el-menu-item>
                    <el-menu-item index="/admin/user">
                        <i class="el-icon-user"/>
                        <span slot="title">用户管理</span>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header style="text-align: right">
                    <el-link href="https://github.com/lincanqiang/bookshop" type="primary" icon="el-icon-help" style="margin: 20px">Github</el-link>
                    <span>管理员</span>
                    <el-dropdown>
                        <i class="el-icon-setting"/>
                        <el-dropdown-menu>
                            <el-dropdown-item  @click.native="logout">退出登录</el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </el-header>
                <el-main>
                    <template>
                        <router-view/>
                    </template>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
       name:"Layout",
        created() {
          if (!sessionStorage.getItem('master')){
              this.$router.push('/admin/login');
          }
        },
        methods:{
           logout() {
               this.$confirm('确认退出?',{})
               .then(() =>{
                   sessionStorage.removeItem('master');
                   this.$router.push('/admin/login');
               })
               .catch(() => {});
           }
        }
    }
</script>

<style scoped>
    .el-header {
        background-color: #B3C0D1;

    }
    .el-container{
        height: 100%;
    }
    .el-aside{
        background-color: #E9EEF3;
    }
</style>