<template>
  <div>
    <el-backtop :bottom="250" :right="20">
      <div style="{height: 100%;width: 100%;background-color: #f2f5f6;box-shadow: 0 0 6px rgba(0,0,0, .12);text-align: center;
            line-height: 40px;color: #1989fa;}">
        <i class="el-icon-upload2"></i>
      </div>
    </el-backtop>

    <div style="margin-top: 20px">
      <el-card class="box-card">
        <div slot="header" style="height: 40px; vertical-align: middle">
                <span style="float: left; margin-top: 8px">
                    <i class="el-icon-search" style="margin-right: 5px"></i>
                    <strong style="font-weight: bolder;font-size: 20px">搜索意中人</strong>
                </span>
          <el-input placeholder="输入ID精准搜索" v-model="idInputSearch"
                    style="width: 200px;float: right; ">
            <i slot="suffix" class="el-input__icon el-icon-search"
               @click="handleIDSearch(idInputSearch)"></i>
          </el-input>
        </div>
        <ul>
          <li style="margin-bottom: 30px"><strong style="font-weight: bolder">热门标签</strong>
            <el-tag v-for="(item,index) in hottestLabel" :key="index"
                    @click="handleLabelSearch(item.label)" class="hottestLabel" effect="plain">
              {{item.label}}
            </el-tag>
          </li>
        </ul>

        <ul>
          <li style="margin-bottom: 30px"><strong style="font-weight: bolder">基本条件</strong>

            <el-select v-model="sex_label" clearable placeholder="性别"
                       style="margin-left: 20px; width: 80px" @change="changeLocationValue_sex">
              <el-option v-for="item in sex" :key="item.sex_value"
                         :label="item.sex_label" :value="item.sex_value"></el-option>
            </el-select>

            <el-select v-model="youngest_label" clearable placeholder="选择年龄范围"
                       style="margin-left: 15px; margin-right:3px; width: 130px"
                       @change="changeLocationValue_youngest">
              <el-option v-for="item in age" :key="item.age_value"
                         :label="item.age_label" :value="item.age_value">
              </el-option>
            </el-select>
            <strong>至</strong>
            <el-select v-model="oldest_label" clearable placeholder="选择年龄范围"
                       style="margin-left: 3px; width: 130px" @change="changeLocationValue_oldest">
              <el-option v-for="item in age" :key="item.age_value"
                         :label="item.age_label" :value="item.age_value">
              </el-option>
            </el-select>

            <el-cascader ref="areaCascader" v-model="addressValue" clearable placeholder="所在地区" :options="area"
                         :props="{ expandTrigger: 'hover' }" @change="handleChange"
                         style="margin-left: 15px; width: 200px">
            </el-cascader>

            <el-select v-model="shortest_label" clearable placeholder="选择身高范围"
                       style="margin-left: 15px; margin-right:3px; width: 180px"
                       @change="changeLocationValue_shortest">
              <el-option v-for="item in height" :key="item.height_value"
                         :label="item.height_label" :value="item.height_value">
              </el-option>
            </el-select>
            <strong>至</strong>
            <el-select v-model="tallest_label" clearable placeholder="选择身高范围"
                       style="margin-left: 3px; width: 180px" @change="changeLocationValue_tallest">
              <el-option v-for="item in height" :key="item.height_value"
                         :label="item.height_label" :value="item.height_value">
              </el-option>
            </el-select>
          </li>
        </ul>

        <ul>
          <li style="margin-bottom: 20px"><strong style="font-weight: bolder">高级搜索</strong>

            <el-select v-model="education_label" clearable placeholder="学历"
                       style="margin-left: 20px; width: 145px" @change="changeLocationValue_education">
              <el-option v-for="item in education" :key="item.education_value"
                         :label="item.education_label" :value="item.education_value"></el-option>
            </el-select>

            <el-select v-model="profession_label" clearable placeholder="职业"
                       style="margin-left: 20px; width: 200px" @change="changeLocationValue_profession">
              <el-option v-for="item in profession" :key="item.profession_value"
                         :label="item.profession_label" :value="item.profession_value"></el-option>
            </el-select>

            <el-select v-model="marrige_label" clearable placeholder="婚姻情况"
                       style="margin-left: 20px; width: 120px" @change="changeLocationValue_marriage">
              <el-option v-for="item in marrige" :key="item.marrige_value"
                         :label="item.marrige_label" :value="item.marrige_value"></el-option>
            </el-select>

            <el-select v-model="income_label" clearable placeholder="选择月收入范围"
                       style="margin-left: 15px; margin-right:3px; width: 250px"
                       @change="changeLocationValue_salary">
              <el-option v-for="item in salary" :key="item.salary_value"
                         :label="item.salary_label" :value="item.salary_value">
              </el-option>
            </el-select>
          </li>
        </ul>

        <el-button class="searchButton" plain type="text" @click="handleDetailSearch()">搜索
        </el-button>
      </el-card>
    </div>

    <div style="margin-top: 20px">
      <el-card class="searchResult">
        <figure v-for="(item,index) in pageInfo.list" :key="index" @click="handleCheck(item.id)">
          <img src="../recommend/sample.jpg"/>
          <h2>{{item.username}}</h2>
        </figure>
      </el-card>
    </div>
    <div style="margin-top: 20px">
      <el-pagination
        background
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageInfo.pageSize"
        layout="prev, pager, next"
        :total="pageInfo.total"
        style="text-align: center">
      </el-pagination>
    </div>

    <div style="margin-top: 100px">
      <el-card shadow="false" style="width: 1600px;margin: 0 auto; border: none">
        <p style="text-align: center">关于我们|联系我们|加入我们|合作伙伴|意见反馈|安全中心|网站地图|帮助中心|精英会员|个人信息保护政策</p>
        <p style="text-align: center">品牌：10年专业婚恋服务 专业：庞大的资深红娘队伍</p>
        <p style="text-align: center">客服热线：4001-520-520（周一至周日：9:00-21:00）客服信箱：1234567890@jingying.com</p>
        <p style="text-align: center">违法和不良信息举报 4001-520-520 举报信箱：1234567891@jingying.com</p>
        <p style="text-align: center">Copyright © 2010-2020 版权所有：西虹市精英婚庆网信息技术有限公司</p>
      </el-card>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import {regionDataPlus, TextToCode} from 'element-china-area-data'

  export default {
    name: 'Search',
    data () {
      return {
        addressValue: [''],
        userId: 0,
        searchType: 2,
        searchForm: {
          id: 1,
          sex: 1,
          youngest: 22,
          oldest: 36,
          address: '北京市/市辖区/朝阳区',
          shortest: 0,
          tallest: 300,
          salary: '10000-20000元',
          education: '',
          profession: '',
          marrige: 0
        },
        currentPage: 1,
        pageInfo: {},
        currentLabel: '',
        idInputSearch: '',
        activeIndex: '3',
        records: {},
        hottestLabel: {},
        userList: {},
        area: '',
        education: [{
          education_value: '选项1',
          education_label: '学历不限'
        }, {
          education_value: '选项2',
          education_label: '高中中专及以下'
        }, {
          education_value: '选项3',
          education_label: '大专'
        }, {
          education_value: '选项4',
          education_label: '本科'
        }, {
          education_value: '选项5',
          education_label: '硕士'
        }, {
          education_value: '选项6',
          education_label: '博士'
        }, {
          education_value: '选项7',
          education_label: '博士后'
        }],
        education_label: '学历不限',
        profession: [{
          profession_value: '选项1',
          profession_label: '职业不限'
        }, {
          profession_value: '选项2',
          profession_label: '销售'
        }, {
          profession_value: '选项3',
          profession_label: '客服'
        }, {
          profession_value: '选项4',
          profession_label: '人事/行政/后勤'
        }, {
          profession_value: '选项5',
          profession_label: '餐饮'
        }, {
          profession_value: '选项6',
          profession_label: '旅游'
        }, {
          profession_value: '选项7',
          profession_label: '酒店'
        }, {
          profession_value: '选项8',
          profession_label: '超市/百货/零售'
        }, {
          profession_value: '选项9',
          profession_label: '美容/美发'
        }, {
          profession_value: '选项10',
          profession_label: '运动健身'
        }, {
          profession_value: '选项11',
          profession_label: '普工/技工'
        }, {
          profession_value: '选项12',
          profession_label: '生产管理/研发'
        }, {
          profession_value: '选项13',
          profession_label: '建筑'
        }, {
          profession_value: '选项14',
          profession_label: '物业管理'
        }, {
          profession_value: '选项15',
          profession_label: '房产中介'
        }, {
          profession_value: '选项16',
          profession_label: '家政保洁/安保'
        }, {
          profession_value: '选项17',
          profession_label: '司机/交通服务'
        }, {
          profession_value: '选项18',
          profession_label: '贸易/采购'
        }, {
          profession_value: '选项19',
          profession_label: '物流/仓储'
        }, {
          profession_value: '选项20',
          profession_label: '淘宝职位'
        }, {
          profession_value: '选项21',
          profession_label: '美术/设计/创意'
        }, {
          profession_value: '选项22',
          profession_label: '市场/媒介/公关'
        }, {
          profession_value: '选项23',
          profession_label: '广告/会展/咨询'
        }, {
          profession_value: '选项24',
          profession_label: '影视/娱乐/休闲'
        }, {
          profession_value: '选项25',
          profession_label: '教育培训'
        }, {
          profession_value: '选项26',
          profession_label: '财务/审计/统计'
        }, {
          profession_value: '选项27',
          profession_label: '法律'
        }, {
          profession_value: '选项28',
          profession_label: '翻译'
        }, {
          profession_value: '选项29',
          profession_label: '编辑/出版/印刷'
        }, {
          profession_value: '选项30',
          profession_label: '计算机/互联网/通信'
        }, {
          profession_value: '选项31',
          profession_label: '电子/电气'
        }, {
          profession_value: '选项32',
          profession_label: '机械/仪器仪表'
        }, {
          profession_value: '选项33',
          profession_label: '金融/银行/证券/投资'
        }, {
          profession_value: '选项34',
          profession_label: '保险'
        }, {
          profession_value: '选项35',
          profession_label: '医院/医疗/护理'
        }, {
          profession_value: '选项36',
          profession_label: '制药/生物工程'
        }, {
          profession_value: '选项37',
          profession_label: '服装/纺织/食品'
        }, {
          profession_value: '选项38',
          profession_label: '环保/能源'
        }, {
          profession_value: '选项39',
          profession_label: '质控/安防'
        }, {
          profession_value: '选项40',
          profession_label: '高级管理'
        }, {
          profession_value: '选项41',
          profession_label: '农/林/牧/渔业'
        }, {
          profession_value: '选项42',
          profession_label: '其他职业'
        }],
        profession_label: '职业不限',
        marrige: [{
          marrige_value: '选项1',
          marrige_label: '不限'
        }, {
          marrige_value: '选项2',
          marrige_label: '未婚'
        }, {
          marrige_value: '选项3',
          marrige_label: '离异'
        }, {
          marrige_value: '选项4',
          marrige_label: '丧偶'
        }],
        marrige_label: '未婚',
        sex: [{
          sex_value: '选项1',
          sex_label: '男'
        }, {
          sex_value: '选项2',
          sex_label: '女'
        }],
        sex_label: '女',
        age: [{
          age_value: '选项1',
          age_label: '年龄不限'
        }, {
          age_value: '选项2',
          age_label: '22'
        }, {
          age_value: '选项3',
          age_label: '23'
        }, {
          age_value: '选项4',
          age_label: '24'
        }, {
          age_value: '选项5',
          age_label: '25'
        }, {
          age_value: '选项6',
          age_label: '26'
        }, {
          age_value: '选项7',
          age_label: '27'
        }, {
          age_value: '选项8',
          age_label: '28'
        }, {
          age_value: '选项9',
          age_label: '29'
        }, {
          age_value: '选项10',
          age_label: '30'
        }, {
          age_value: '选项11',
          age_label: '31'
        }, {
          age_value: '选项12',
          age_label: '32'
        }, {
          age_value: '选项13',
          age_label: '33'
        }, {
          age_value: '选项14',
          age_label: '34'
        }, {
          age_value: '选项15',
          age_label: '35'
        }, {
          age_value: '选项16',
          age_label: '36'
        }, {
          age_value: '选项17',
          age_label: '37'
        }, {
          age_value: '选项18',
          age_label: '38'
        }, {
          age_value: '选项19',
          age_label: '39'
        }, {
          age_value: '选项20',
          age_label: '40'
        }],
        youngest_label: '22',
        oldest_label: '36',
        height: [{
          height_value: '选项1',
          height_label: '身高不限'
        }, {
          height_value: '选项2',
          height_label: '140'
        }, {
          height_value: '选项3',
          height_label: '145'
        }, {
          height_value: '选项4',
          height_label: '150'
        }, {
          height_value: '选项5',
          height_label: '155'
        }, {
          height_value: '选项6',
          height_label: '160'
        }, {
          height_value: '选项7',
          height_label: '165'
        }, {
          height_value: '选项8',
          height_label: '170'
        }, {
          height_value: '选项9',
          height_label: '175'
        }, {
          height_value: '选项10',
          height_label: '180'
        }, {
          height_value: '选项11',
          height_label: '185'
        }, {
          height_value: '选项12',
          height_label: '190'
        }, {
          height_value: '选项13',
          height_label: '195'
        }, {
          height_value: '选项14',
          height_label: '200'
        }],
        shortest_label: '身高不限',
        tallest_label: '身高不限',
        salary: [{
          salary_value: '选项1',
          salary_label: '收入不限'
        }, {
          salary_value: '选项2',
          salary_label: '2000元以下'
        }, {
          salary_value: '选项3',
          salary_label: '2000-5000元'
        }, {
          salary_value: '选项4',
          salary_label: '5000-10000元'
        }, {
          salary_value: '选项5',
          salary_label: '10000-20000元'
        }, {
          salary_value: '选项6',
          salary_label: '20000-50000元'
        }, {
          salary_value: '选项7',
          salary_label: '50000元以上'
        }],
        income_label: '10000-20000元',
      }
    },
    created () {
      this.area = regionDataPlus
      this.getHottestLabel()
      this.getData()
    },
    methods: {
      getHottestLabel () {//获取热门标签
        let url = '/labelHeat/heat_list'
        axios.get(url).then((res) => {
          // console.log(res)
          this.hottestLabel = res.data
          // console.log(url)
        })
      },
      handleLabelSearch (label) {
        this.searchType = 1
        this.currentLabel = label
        let url = `/user/label_search/${label}&${this.userId}`
        axios.get(url).then((res) => {
          //console.log(res)
          this.userList = res.data
          // console.log(this.userList)
          this.getLabelSearchData()
        })
      },
      getLabelSearchData () {//post请求获取分页显示的pageInfo
        this.searchType = 1
        let url = `/user/queryLabelSearch/${this.currentPage}&${this.currentLabel}&${this.userId}`
        // console.log(`d3+${this.currentPage}+${this.currentLabel}`)
        axios.get(url).then((res) => {
          //console.log(res)
          this.pageInfo = res.data
          // console.log(this.pageInfo)
        })
      },
      getData () {//post请求获取分页显示的pageInfo
        let url_getCurrentUser = '/getCurrentUser'
        axios.get(url_getCurrentUser).then((res) => {
          // console.log(res.data.message)
          if(res.data){//没登录的用户，游客所能看到的默认界面

          }
          else {//获取到当前登录用户
            this.userId = res.data.message.userid
            //根据择偶要求设置默认搜索界面的结果，并显示在选择框上
            let url_1 = `/date_standard/select/${this.userId}`
            axios.get(url_1).then((res) => {
              // console.log(res)
              let areas = res.data.address.split('/')
              console.log(areas)
              if (areas.length === 3) {//xx省/xx市/xx区
                this.searchForm.address = areas[0] + '/' + areas[1] + '/' + areas[2]
                let province = TextToCode[areas[0]].code
                let city = TextToCode[areas[0]][areas[1]].code
                let area = TextToCode[areas[0]][areas[1]][areas[2]].code
                this.addressValue = [province, city, area]
              } else if (areas.length === 2) {//xx省/xx市/全部
                this.searchForm.address = areas[0] + '/' + areas[1]
                let province = TextToCode[areas[0]].code
                // console.log(province)
                let city = TextToCode[areas[0]][areas[1]].code
                let area = ''
                this.addressValue = [province, city, area]
                console.log(this.addressValue)
              } else if (areas.length === 0) {//不限
                this.searchForm.address = ''
                this.addressValue = []
              } else if (areas.length === 1) {//xx省/全部
                this.searchForm.address = areas[0]
                let province = TextToCode[areas[0]].code + ''
                let city = ''
                this.addressValue = [province, city]
              }

              this.searchForm.youngest = res.data.agemin
              this.youngest_label = res.data.agemin

              this.searchForm.oldest = res.data.agemax
              this.oldest_label = res.data.agemax
              // console.log(url)
              let url_2 = `/user/get/${this.userId}`
              axios.get(url_2).then((res) => {
                // console.log(res)
                if (res.data.sex === 0) {
                  this.searchForm.sex = 1
                  this.sex_label = '女'
                } else {
                  this.searchForm.sex = 0
                  this.sex_label = '男'
                }
                // console.log(url)
                let url = `/user/queryDetailSearch/${this.currentPage}`
                this.$axios.post(url, this.searchForm).then((res) => {
                  // console.log(this.searchForm)
                  this.pageInfo = res.data
                  console.log(this.pageInfo)
                })
              })
            })
          }
        })
      },
      handleDetailSearch () {
        this.searchType = 2
        let url = `/user/queryDetailSearch/${this.currentPage}`
        this.$axios.post(url, this.searchForm).then((res) => {
          // console.log(this.searchForm)
          this.pageInfo = res.data
          console.log(this.pageInfo)
        })
      },
      handleCurrentChange (val) {
        this.currentPage = val
        // console.log(`d1+${this.currentPage}`)
        if (this.searchType === 1) {
          this.getLabelSearchData()
          // console.log(`d2+${this.currentPage}`)
        } else if (this.searchType === 2) {
          this.handleDetailSearch()
        }
      },
      handleChange () {//地区的级联选择器
        let address = ''
        let temp = this.$refs.areaCascader.getCheckedNodes()[0].pathLabels
        console.log(this.$refs.areaCascader.getCheckedNodes())
        if (temp[0] === '全部') {
          address = ''
        } else if (temp[1] === '全部') {
          address = temp[0] + ''
        } else if (temp[2] === '全部') {
          address = temp[0] + '/' + temp[1] + ''
        } else {
          address = temp[0] + '/' + temp[1] + '/' + temp[2]
        }
        // console.log(address)
        this.searchForm.address = address
      },
      changeLocationValue_sex (val) {
        let obj = {}
        obj = this.sex.find((item) => {
          return item.sex_value === val
        })
        // console.log(obj)
        let getSex = ''
        getSex = obj.sex_label
        // console.log(getName)
        if (getSex === '男') {
          this.searchForm.sex = 0
        } else if (getSex === '女') {
          this.searchForm.sex = 1
        }
      },
      changeLocationValue_youngest (val) {
        let obj = {}
        obj = this.age.find((item) => {
          return item.age_value === val
        })
        // console.log(obj)
        if (obj.age_label === '年龄不限') {
          this.searchForm.youngest = 0
        } else {
          this.searchForm.youngest = parseInt(obj.age_label)
          // console.log(parseInt(obj.salary_label))
        }
      },
      changeLocationValue_oldest (val) {
        let obj = {}
        obj = this.age.find((item) => {
          return item.age_value === val
        })
        // console.log(obj)
        if (obj.age_label === '年龄不限') {
          this.searchForm.oldest = 50
        } else {
          this.searchForm.oldest = parseInt(obj.age_label)
          // console.log(parseInt(obj.salary_label))
        }
      },
      changeLocationValue_shortest (val) {
        let obj = {}
        obj = this.height.find((item) => {
          return item.height_value === val
        })
        // console.log(obj)
        if (obj.height_label === '身高不限') {
          this.searchForm.shortest = 0
        } else {
          this.searchForm.shortest = parseInt(obj.height_label)
          // console.log(parseInt(obj.salary_label))
        }
      },
      changeLocationValue_tallest (val) {
        let obj = {}
        obj = this.height.find((item) => {
          return item.height_value === val
        })
        // console.log(obj)
        if (obj.height_label === '身高不限') {
          this.searchForm.tallest = 300
        } else {
          this.searchForm.tallest = parseInt(obj.height_label)
          // console.log(parseInt(obj.salary_label))
        }
      },
      changeLocationValue_salary (val) {
        let obj = {}
        obj = this.salary.find((item) => {
          return item.salary_value === val
        })
        // console.log(obj)
        if (obj.salary_label === '收入不限') {
          this.searchForm.salary = ''
        } else {
          this.searchForm.salary = obj.salary_label
          // console.log(parseInt(this.searchForm.lowest))
        }
      },
      changeLocationValue_education (val) {
        let obj = {}
        obj = this.education.find((item) => {
          return item.education_value === val
        })
        // console.log(obj)
        if (obj.education_label === '学历不限') {
          this.searchForm.education = ''
        } else {
          this.searchForm.education = obj.education_label
          // console.log(parseInt(this.searchForm.lowest))
        }
      },
      changeLocationValue_profession (val) {
        let obj = {}
        obj = this.profession.find((item) => {
          return item.profession_value === val
        })
        // console.log(obj)
        if (obj.profession_label === '职业不限') {
          this.searchForm.profession = ''
        } else {
          this.searchForm.profession = obj.profession_label
          // console.log(parseInt(this.searchForm.lowest))
        }
      },
      changeLocationValue_marriage (val) {
        let obj = {}
        obj = this.marrige.find((item) => {
          return item.marrige_value === val
        })
        // console.log(obj)
        let getMarriage = ''
        getMarriage = obj.marrige_label
        // console.log(getYoungestAge)
        if (getMarriage === '未婚') {
          this.searchForm.marrige = 0
        } else if (getMarriage === '离异') {
          this.searchForm.marrige = 1
        } else if (getMarriage === '丧偶') {
          this.searchForm.marrige = 2
        } else {
          this.searchForm.marrige = 3
        }
      },
      handleCheck (id) {
        this.$router.push({
          path: `/check/${id}`
        })
      },
      handleIDSearch (id) {
        if (id === '') {
          this.$message('请输入ID')
        } else {
          let url = `/user/get/${id}`
          axios.get(url).then((res) => {
            // console.log(res.data)
            if (res.data === '') {
              this.$message('该用户不存在')
            } else {
              this.$router.push({
                path: `/check/${id}`
              })
            }
          })
        }
      }
    }
  }
</script>

<style scoped>
  .hottestLabel {
    width: 80px;
    margin-left: 20px;
    text-align: center;
    color: black;
    border-radius: 30px
  }

  .box-card {
    width: 1250px;
    margin: 0 auto;
  }

  .searchResult {
    width: 1250px;
    margin: 0 auto;
  }

  figure {
    width: 220px;
    float: left;
    padding: 2px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-top: 1px;
  }

  figure img {
    width: 220px;
    height: 200px;
  }

  figure h2 {
    text-align: center;
  }

  .searchButton {
    background-color: mediumpurple;
    width: 300px;
    height: 45px;
    color: white;
    font-size: 16px;
    margin-left: 40%
  }
</style>
