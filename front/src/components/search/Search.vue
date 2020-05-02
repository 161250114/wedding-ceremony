<template>
  <div>
    <el-backtop :bottom="250" :right="20">
      <div style="{height: 100%;width: 100%;background-color: #f2f5f6;box-shadow: 0 0 6px rgba(0,0,0, .12);text-align: center;
            line-height: 40px;color: #1989fa;}">
        <i class="el-icon-upload2"></i>
      </div>
    </el-backtop>

<!--            <Head :index="activeIndex"></Head>-->

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

            <el-cascader ref="address" v-model="value" clearable placeholder="所在地区" :options="place"
                         :props="{ expandTrigger: 'hover' }" @change="handleChange"
                         style="margin-left: 15px; width: 160px">
            </el-cascader>

            <el-select v-model="shortest_label" clearable placeholder="选择身高范围"
                       style="margin-left: 15px; margin-right:3px; width: 200px"
                       @change="changeLocationValue_shortest">
              <el-option v-for="item in height" :key="item.height_value"
                         :label="item.height_label" :value="item.height_value">
              </el-option>
            </el-select>
            <strong>至</strong>
            <el-select v-model="tallest_label" clearable placeholder="选择身高范围"
                       style="margin-left: 3px; width: 200px" @change="changeLocationValue_tallest">
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
<!--            <strong>至</strong>-->
<!--            <el-select v-model="highest_label" clearable placeholder="选择月收入范围"-->
<!--                       style="margin-left: 3px; width: 145px" @change="changeLocationValue_highest">-->
<!--              <el-option v-for="item in salary" :key="item.salary_value"-->
<!--                         :label="item.salary_label" :value="item.salary_value">-->
<!--              </el-option>-->
<!--            </el-select>-->

          </li>
        </ul>

        <el-button class="searchButton" plain type="text" @click="getDetailSearchData">搜索
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
        <p style="text-align: center">关于我们|联系我们|加入我们|合作伙伴| 意见反馈|安全中心|网站地图 | 帮助中心|精英会员|个人信息保护政策</p>
        <p style="text-align: center">品牌：10年专业婚恋服务 专业：庞大的资深红娘队伍</p>
        <p style="text-align: center">客服热线：4001-520-520（周一至周日：9:00-21:00）客服信箱：1234567890@jingying.com</p>
        <p style="text-align: center">违法和不良信息举报 4001-520-520 举报信箱：1234567891@jingying.com</p>
        <p style="text-align: center">Copyright © 2010-2020 版权所有：西虹市精英婚庆网信息技术有限公司</p>
      </el-card>
    </div>
  </div>
</template>

<script>
  import Head from '../head/Head'
  import axios from 'axios'

  export default {
    name: 'Search',
    data () {
      return {
        userId: 111,
        searchType: 1,
        searchForm: {
          id: 1,
          sex: 1,
          youngest: 22,
          oldest: 36,
          address: '%江苏苏州%',
          shortest: 0,
          tallest: 300,
          salary: '10000 ~ 20000元',
          education: '本科',
          profession: '教育',
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
        education: [{
          education_value: '选项1',
          education_label: '高中中专及以下'
        }, {
          education_value: '选项2',
          education_label: '大专'
        }, {
          education_value: '选项3',
          education_label: '本科'
        }, {
          education_value: '选项4',
          education_label: '硕士'
        }, {
          education_value: '选项5',
          education_label: '博士'
        }, {
          education_value: '选项6',
          education_label: '博士后'
        }],
        education_label: '本科',
        profession: [{
          profession_value: '选项1',
          profession_label: '在校学生'
        }, {
          profession_value: '选项2',
          profession_label: '计算机/互联网/IT'
        }, {
          profession_value: '选项3',
          profession_label: '电子/半导体/仪表仪器'
        }, {
          profession_value: '选项4',
          profession_label: '通信技术'
        }, {
          profession_value: '选项5',
          profession_label: '销售'
        }, {
          profession_value: '选项6',
          profession_label: '教育'
        }, {
          profession_value: '选项7',
          profession_label: '公关/商务'
        }, {
          profession_value: '选项8',
          profession_label: '采购/贸易'
        }, {
          profession_value: '选项9',
          profession_label: '客户服务/技术支持'
        }, {
          profession_value: '选项10',
          profession_label: '人力资源/行政/后勤'
        }, {
          profession_value: '选项11',
          profession_label: '高级管理'
        }, {
          profession_value: '选项12',
          profession_label: '生产/加工/制造'
        }, {
          profession_value: '选项13',
          profession_label: '质控/安检'
        }, {
          profession_value: '选项14',
          profession_label: '工程机械'
        }, {
          profession_value: '选项15',
          profession_label: '技工'
        }, {
          profession_value: '选项16',
          profession_label: '财会/审计/统计'
        }, {
          profession_value: '选项17',
          profession_label: '金融/证券/投资/保险'
        }, {
          profession_value: '选项18',
          profession_label: '公务员/国家干部'
        }, {
          profession_value: '选项19',
          profession_label: '自由职业者'
        }, {
          profession_value: '选项20',
          profession_label: '其他'
        }],
        profession_label: '教育',
        nation: [{
          nation_value: '选项1',
          nation_label: '汉族'
        }, {
          nation_value: '选项2',
          nation_label: '藏族'
        }, {
          nation_value: '选项3',
          nation_label: '朝鲜族'
        }, {
          nation_value: '选项4',
          nation_label: '蒙古族'
        }, {
          nation_value: '选项5',
          nation_label: '回族'
        }, {
          nation_value: '选项6',
          nation_label: '满族'
        }, {
          nation_value: '选项7',
          nation_label: '维吾尔族'
        }, {
          nation_value: '选项8',
          nation_label: '彝族'
        }, {
          nation_value: '选项9',
          nation_label: '苗族'
        }, {
          nation_value: '选项10',
          nation_label: '壮族'
        }, {
          nation_value: '选项11',
          nation_label: '其他'
        }],
        nation_label: '汉族',
        marrige: [{
          marrige_value: '选项1',
          marrige_label: '未婚'
        }, {
          marrige_value: '选项2',
          marrige_label: '离异'
        }, {
          marrige_value: '选项3',
          marrige_label: '丧偶'
        }, {
          marrige_value: '选项4',
          marrige_label: '未婚、离异'
        }, {
          marrige_value: '选项5',
          marrige_label: '未婚、丧偶'
        }, {
          marrige_value: '选项6',
          marrige_label: '离异、丧偶'
        }],
        marrige_label: '未婚',
        house: [{
          house_value: '选项1',
          house_label: '未购房'
        }, {
          house_value: '选项2',
          house_label: '需要时购置'
        }, {
          house_value: '选项3',
          house_label: '已购房(有贷款)'
        }, {
          house_value: '选项4',
          house_label: '已购房(无贷款)'
        }, {
          house_value: '选项5',
          house_label: '已购房'
        }, {
          house_value: '选项6',
          house_label: '与人合租'
        }, {
          house_value: '选项7',
          house_label: '独自租房'
        }, {
          house_value: '选项8',
          house_label: '与父母同住'
        }, {
          house_value: '选项9',
          house_label: '住亲朋家'
        }, {
          house_value: '选项10',
          house_label: '住单位房'
        }],
        house_label: '未购房',
        child: [{
          child_value: '选项1',
          child_label: '没有孩子'
        }, {
          child_value: '选项2',
          child_label: '有孩子跟着自己'
        }, {
          child_value: '选项3',
          child_label: '有孩子跟着对方'
        }, {
          child_value: '选项4',
          child_label: '有孩子但已经独立'
        }],
        child_label: '没有孩子',
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
        value: ['选项11', '选项2'],
        place: [{
          value: '选项1',
          label: '地区不限'
        }, {
          value: '选项2',
          label: '北京',
          children: [{
            value: '选项1',
            label: '地区不限',
          }, {
            value: '选项2',
            label: '东城',
          }, {
            value: '选项3',
            label: '西城',
          }, {
            value: '选项4',
            label: '朝阳',
          }, {
            value: '选项5',
            label: '丰台',
          }, {
            value: '选项6',
            label: '石景山',
          }, {
            value: '选项7',
            label: '海淀',
          }, {
            value: '选项8',
            label: '门头沟',
          }, {
            value: '选项9',
            label: '房山',
          }, {
            value: '选项10',
            label: '通州',
          }, {
            value: '选项11',
            label: '顺义',
          }, {
            value: '选项12',
            label: '昌平',
          }, {
            value: '选项13',
            label: '大兴',
          }, {
            value: '选项14',
            label: '平谷',
          }, {
            value: '选项15',
            label: '怀柔',
          }, {
            value: '选项16',
            label: '密云',
          }, {
            value: '选项17',
            label: '延庆',
          }]
        }, {
          value: '选项3',
          label: '天津',
          children: [{
            value: '选项1',
            label: '地区不限',
          }, {
            value: '选项2',
            label: '和平',
          }, {
            value: '选项3',
            label: '河东',
          }, {
            value: '选项4',
            label: '河西',
          }, {
            value: '选项5',
            label: '南开',
          }, {
            value: '选项6',
            label: '河北',
          }, {
            value: '选项7',
            label: '红桥',
          }, {
            value: '选项8',
            label: '滨海新区',
          }, {
            value: '选项9',
            label: '东丽',
          }, {
            value: '选项10',
            label: '西青',
          }, {
            value: '选项11',
            label: '津南',
          }, {
            value: '选项12',
            label: '北辰',
          }, {
            value: '选项13',
            label: '宁河',
          }, {
            value: '选项14',
            label: '武清',
          }, {
            value: '选项15',
            label: '静海',
          }, {
            value: '选项16',
            label: '宝坻',
          }, {
            value: '选项17',
            label: '蓟州',
          }]
        }, {
          value: '选项4',
          label: '河北',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '石家庄'
          }, {
            value: '选项3',
            label: '唐山'
          }, {
            value: '选项4',
            label: '秦皇岛'
          }, {
            value: '选项5',
            label: '邯郸'
          }, {
            value: '选项6',
            label: '邢台'
          }, {
            value: '选项7',
            label: '保定'
          }, {
            value: '选项8',
            label: '张家口'
          }, {
            value: '选项9',
            label: '承德'
          }, {
            value: '选项10',
            label: '沧州'
          }, {
            value: '选项11',
            label: '廊坊'
          }, {
            value: '选项12',
            label: '衡水'
          }]
        }, {
          value: '选项5',
          label: '山西',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '太原'
          }, {
            value: '选项2',
            label: '大同'
          }, {
            value: '选项2',
            label: '阳泉'
          }, {
            value: '选项2',
            label: '长治'
          }, {
            value: '选项2',
            label: '晋城'
          }, {
            value: '选项2',
            label: '朔州'
          }, {
            value: '选项2',
            label: '晋中'
          }, {
            value: '选项2',
            label: '运城'
          }, {
            value: '选项2',
            label: '忻州'
          }, {
            value: '选项2',
            label: '临汾'
          }, {
            value: '选项2',
            label: '吕梁'
          }]
        }, {
          value: '选项6',
          label: '内蒙古',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '赤峰'
          }, {
            value: '选项3',
            label: '包头'
          }, {
            value: '选项4',
            label: '通辽'
          }, {
            value: '选项5',
            label: '呼和浩特'
          }, {
            value: '选项6',
            label: '鄂尔多斯'
          }, {
            value: '选项7',
            label: '乌海'
          }, {
            value: '选项8',
            label: '呼伦贝尔'
          }, {
            value: '选项9',
            label: '兴安盟'
          }, {
            value: '选项10',
            label: '巴彦淖尔盟'
          }, {
            value: '选项11',
            label: '乌兰察布盟'
          }, {
            value: '选项12',
            label: '锡林郭勒盟'
          }, {
            value: '选项13',
            label: '阿拉善盟'
          }]
        }, {
          value: '选项7',
          label: '辽宁',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '大连'
          }, {
            value: '选项3',
            label: '沈阳'
          }, {
            value: '选项4',
            label: '丹东'
          }, {
            value: '选项5',
            label: '辽阳'
          }, {
            value: '选项6',
            label: '葫芦岛'
          }, {
            value: '选项7',
            label: '锦州'
          }, {
            value: '选项8',
            label: '朝阳'
          }, {
            value: '选项9',
            label: '营口'
          }, {
            value: '选项10',
            label: '鞍山'
          }, {
            value: '选项11',
            label: '抚顺'
          }, {
            value: '选项12',
            label: '阜新'
          }, {
            value: '选项13',
            label: '盘锦'
          }, {
            value: '选项14',
            label: '本溪'
          }, {
            value: '选项15',
            label: '铁岭'
          }]
        }, {
          value: '选项8',
          label: '吉林',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '吉林'
          }, {
            value: '选项3',
            label: '长春'
          }, {
            value: '选项4',
            label: '白山'
          }, {
            value: '选项5',
            label: '延边州'
          }, {
            value: '选项6',
            label: '白城'
          }, {
            value: '选项7',
            label: '松原'
          }, {
            value: '选项8',
            label: '辽源'
          }, {
            value: '选项9',
            label: '通化'
          }, {
            value: '选项10',
            label: '四平'
          }]
        }, {
          value: '选项9',
          label: '黑龙江',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '齐齐哈尔'
          }, {
            value: '选项3',
            label: '哈尔滨'
          }, {
            value: '选项4',
            label: '大庆'
          }, {
            value: '选项5',
            label: '佳木斯'
          }, {
            value: '选项6',
            label: '双鸭山'
          }, {
            value: '选项7',
            label: '牡丹江'
          }, {
            value: '选项8',
            label: '鸡西'
          }, {
            value: '选项9',
            label: '黑河'
          }, {
            value: '选项10',
            label: '绥化'
          }, {
            value: '选项11',
            label: '鹤岗'
          }, {
            value: '选项12',
            label: '伊春'
          }, {
            value: '选项13',
            label: '大兴安岭地区'
          }, {
            value: '选项14',
            label: '七台河'
          }]
        }, {
          value: '选项10',
          label: '上海',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '松江'
          }, {
            value: '选项3',
            label: '宝山'
          }, {
            value: '选项4',
            label: '金山'
          }, {
            value: '选项5',
            label: '嘉定'
          }, {
            value: '选项6',
            label: '南汇'
          }, {
            value: '选项7',
            label: '青浦'
          }, {
            value: '选项8',
            label: '浦东'
          }, {
            value: '选项9',
            label: '奉贤'
          }, {
            value: '选项10',
            label: '徐汇'
          }, {
            value: '选项11',
            label: '静安'
          }, {
            value: '选项12',
            label: '闵行'
          }, {
            value: '选项13',
            label: '黄浦'
          }, {
            value: '选项14',
            label: '杨浦'
          }, {
            value: '选项15',
            label: '虹口'
          }, {
            value: '选项16',
            label: '普陀'
          }, {
            value: '选项17',
            label: '闸北'
          }, {
            value: '选项18',
            label: '长宁'
          }, {
            value: '选项19',
            label: '崇明'
          }, {
            value: '选项20',
            label: '卢湾'
          }]
        }, {
          value: '选项11',
          label: '江苏',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '苏州'
          }, {
            value: '选项3',
            label: '徐州'
          }, {
            value: '选项4',
            label: '盐城'
          }, {
            value: '选项5',
            label: '无锡'
          }, {
            value: '选项6',
            label: '南京'
          }, {
            value: '选项7',
            label: '南通'
          }, {
            value: '选项8',
            label: '连云港'
          }, {
            value: '选项9',
            label: '常州'
          }, {
            value: '选项10',
            label: '镇江'
          }, {
            value: '选项11',
            label: '扬州'
          }, {
            value: '选项12',
            label: '淮安'
          }, {
            value: '选项13',
            label: '泰州'
          }, {
            value: '选项14',
            label: '宿迁'
          }]
        }, {
          value: '选项12',
          label: '浙江',
          children: [{
            value: '选项1',
            label: '地区不限'
          }, {
            value: '选项2',
            label: '温州'
          }, {
            value: '选项3',
            label: '宁波'
          }, {
            value: '选项4',
            label: '杭州'
          }, {
            value: '选项5',
            label: '台州'
          }, {
            value: '选项6',
            label: '嘉兴'
          }, {
            value: '选项7',
            label: '金华'
          }, {
            value: '选项8',
            label: '湖州'
          }, {
            value: '选项9',
            label: '绍兴'
          }, {
            value: '选项10',
            label: '舟山'
          }, {
            value: '选项11',
            label: '丽水'
          }, {
            value: '选项12',
            label: '衢州'
          }]
        }, {
          value: '选项13',
          label: '安徽',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项14',
          label: '福建',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项15',
          label: '江西',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项16',
          label: '山东',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项17',
          label: '湖北',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项18',
          label: '湖南',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项19',
          label: '广东',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项20',
          label: '广西',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项21',
          label: '海南',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项22',
          label: '重庆',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项23',
          label: '四川',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项24',
          label: '贵州',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项25',
          label: '云南',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项26',
          label: '西藏',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项27',
          label: '陕西',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项28',
          label: '甘肃',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项29',
          label: '青海',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项30',
          label: '宁夏',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项31',
          label: '新疆',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项32',
          label: '河南',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项33',
          label: '台湾',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项34',
          label: '香港',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项35',
          label: '澳门',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }, {
          value: '选项36',
          label: '国外',
          children: [{
            value: '选项1',
            label: '地区不限'
          }]
        }
        ],
        salary: [{
          salary_value: '选项1',
          salary_label: '收入不限'
        }, {
          salary_value: '选项2',
          salary_label: '2000元以下'
        }, {
          salary_value: '选项3',
          salary_label: '2000 ~ 5000元'
        }, {
          salary_value: '选项4',
          salary_label: '5000 ~ 10000元'
        }, {
          salary_value: '选项5',
          salary_label: '10000 ~ 20000元'
        }, {
          salary_value: '选项6',
          salary_label: '20000 ~ 50000元'
        }, {
          salary_value: '选项7',
          salary_label: '50000元以上'
        }],
        income_label: '10000 ~ 20000元',
      }
    },
    created () {
      this.getHottestLabel()
      this.getDetailSearchData()
    },
    components: {
      Head
    },
    methods: {
      getHottestLabel () {//获取热门标签
        let url = 'http://localhost:8999/wedding/labelHeat/heat_list'
        axios.get(url).then((res) => {
          // console.log(res)
          this.hottestLabel = res.data
          // console.log(url)
        })
      },
      handleLabelSearch (label) {
        this.searchType = 1
        this.currentLabel = label
        let url = `http://localhost:8999/wedding/user/label_search/${label}`
        axios.get(url).then((res) => {
          //console.log(res)
          this.userList = res.data
          // console.log(this.userList)
          this.getLabelSearchData()
        })
      },
      getLabelSearchData () {//post请求获取分页显示的pageInfo
        this.searchType = 1
        let url = `http://localhost:8999/wedding/user/queryLabelSearch/${this.currentPage}&${this.currentLabel}`
        // console.log(`d3+${this.currentPage}+${this.currentLabel}`)
        axios.get(url).then((res) => {
          //console.log(res)
          this.pageInfo = res.data
          // console.log(this.pageInfo)
        })
      },

      getDetailSearchData () {//post请求获取分页显示的pageInfo
        this.searchType = 2
        let url_1 = `http://localhost:8999/wedding/date_standard/select/${this.userId}`
        axios.get(url_1).then((res) => {
          // console.log(res)
          this.searchForm.address = res.data.address
          this.
          this.searchForm.youngest = res.data.agemin
          this.searchForm.oldest = res.data.agemax
          // console.log(url)
        })
        let url_2 = `http://localhost:8999/wedding/user/get/${this.userId}`
        axios.get(url_2).then((res) => {
          // console.log(res)
          if(res.data.sex === 0){
            this.searchForm.sex = 1
          }
          else{
            this.searchForm.sex = 0
          }
          // console.log(url)
        })
        let url = `http://localhost:8999/wedding/user/queryDetailSearch/${this.currentPage}`
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
          this.getDetailSearchData()
        }
      },
      handleChange (value) {//地区的级联选择器
        let obj = {}
        obj = this.place.find((item) => {
          return item.value === value[0]
        })
        let getFirstAddress = ''
        getFirstAddress = obj.label
        if (getFirstAddress === '地区不限') {
          this.searchForm.address = '%%'
        } else {
          let obj2 = {}
          obj2 = obj.children.find((item) => {
            return item.value === value[1]
          })
          let getSecondAddress = ''
          getSecondAddress = obj2.label
          if (getSecondAddress === '地区不限') {
            this.searchForm.address = '%' + getFirstAddress + '%'
          } else {
            this.searchForm.address = '%' + getFirstAddress + getSecondAddress + '%'
          }
        }
        // console.log(this.searchForm.address)
      },
      changeLocationValue_sex (val) {
        //locations是v-for里面的,也是datas里面的值
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
        //locations是v-for里面的,也是datas里面的值
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
        //locations是v-for里面的,也是datas里面的值
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
        //locations是v-for里面的,也是datas里面的值
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
        //locations是v-for里面的,也是datas里面的值
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
        //locations是v-for里面的,也是datas里面的值
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
      // changeLocationValue_highest (val) {
      //   //locations是v-for里面的,也是datas里面的值
      //   let obj = {}
      //   obj = this.salary.find((item) => {
      //     return item.salary_value === val
      //   })
      //   // console.log(obj)
      //   if (obj.salary_label === '收入不限') {
      //     this.searchForm.highest = 1000000
      //   } else {
      //     this.searchForm.highest = parseInt(obj.salary_label)
      //     // console.log(parseInt(this.searchForm.highest))
      //   }
      // },
      changeLocationValue_education (val) {
        //locations是v-for里面的,也是datas里面的值
        let obj = {}
        obj = this.education.find((item) => {
          return item.education_value === val
        })
        // console.log(obj)
        this.searchForm.education = obj.education_label
      },
      changeLocationValue_profession (val) {
        //locations是v-for里面的,也是datas里面的值
        let obj = {}
        obj = this.profession.find((item) => {
          return item.profession_value === val
        })
        // console.log(obj)
        this.searchForm.profession = obj.profession_label
      },
      changeLocationValue_marriage (val) {
        //locations是v-for里面的,也是datas里面的值
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
        } else {
          this.searchForm.marrige = 1
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
          let url = `http://localhost:8999/wedding/user/get/${id}`
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
