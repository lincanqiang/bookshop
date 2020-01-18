<template>
    <div>
        <el-table :data="bean">
            <el-table-column
                    prop="id"
                    label="用户ID"/>
            <el-table-column
                    prop="name"
                    label="用户名"/>
        </el-table>
        <el-col :span="24">
            <el-pagination
                    background
                    layout="total,prev,pager,next"
                    :page-size="5"
                    :pager-count="5"
                    @current-change="handleCurrentChange"
                    :total="total"
                    :current-page="pageNum"
            />
        </el-col>
    </div>
</template>

<script>
    export default {
        name: "User",
        data() {
            return {
                uri: 'users',
                total: 0,
                pageNum: 0,
                bean: [],
            }
        },

        created() {
            this.list(1);
        },

        methods: {
            list(pageNum) {
                let url = this.uri + "?pageNum=" + pageNum;
                this.axios.get(url)
                    .then(response => {
                        this.bean = response.data.content;
                        this.total = response.data.totalElements;
                    })
            },
            handleCurrentChange(val) {
                this.pageNum = val;
                this.list(val);
            }
        }
    }
</script>

<style scoped>

</style>