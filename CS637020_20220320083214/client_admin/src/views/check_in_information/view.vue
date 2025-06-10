<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','user') || $check_field('add','user') || $check_field('set','user')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="用户" prop="user">
						<el-select v-if="user_group === '管理员' || (form['check_in_information_id'] && $check_field('set','user')) || (!form['check_in_information_id'] && $check_field('add','user'))" id="user" v-model="form['user']" :disabled="disabledObj['user_isDisabled']">
							<el-option v-for="o in list_user_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','user')" id="user" v-model="form['user']" :disabled="true">
							<el-option v-for="o in list_user_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['check_in_information_id'] && $check_field('set','full_name')) || (!form['check_in_information_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_information') || $check_field('add','contact_information') || $check_field('set','contact_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系方式" prop="contact_information">
					<el-input id="contact_information" v-model="form['contact_information']" placeholder="请输入联系方式"
							  v-if="user_group === '管理员' || (form['check_in_information_id'] && $check_field('set','contact_information')) || (!form['check_in_information_id'] && $check_field('add','contact_information'))" :disabled="disabledObj['contact_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_information')">{{form['contact_information']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_study_room') || $check_field('add','name_of_study_room') || $check_field('set','name_of_study_room')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="自习室名称" prop="name_of_study_room">
					<el-input id="name_of_study_room" v-model="form['name_of_study_room']" placeholder="请输入自习室名称"
							  v-if="user_group === '管理员' || (form['check_in_information_id'] && $check_field('set','name_of_study_room')) || (!form['check_in_information_id'] && $check_field('add','name_of_study_room'))" :disabled="disabledObj['name_of_study_room_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_study_room')">{{form['name_of_study_room']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','appointment_period') || $check_field('add','appointment_period') || $check_field('set','appointment_period')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约时段" prop="appointment_period">
					<el-input id="appointment_period" v-model="form['appointment_period']" placeholder="请输入预约时段"
							  v-if="user_group === '管理员' || (form['check_in_information_id'] && $check_field('set','appointment_period')) || (!form['check_in_information_id'] && $check_field('add','appointment_period'))" :disabled="disabledObj['appointment_period_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','appointment_period')">{{form['appointment_period']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_appointments') || $check_field('add','number_of_appointments') || $check_field('set','number_of_appointments')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约人数" prop="number_of_appointments">
					<el-input-number id="number_of_appointments" v-model.number="form['number_of_appointments']"
						v-if="user_group === '管理员' || (form['check_in_information_id'] && $check_field('set','number_of_appointments')) || (!form['check_in_information_id'] && $check_field('add','number_of_appointments'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_appointments')">{{form['number_of_appointments']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','administrator_confirmation') || $check_field('add','administrator_confirmation') || $check_field('set','administrator_confirmation')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="管理员确认" prop="administrator_confirmation">
					<el-input id="administrator_confirmation" v-model="form['administrator_confirmation']" placeholder="请输入管理员确认"
							  v-if="user_group === '管理员' || (form['check_in_information_id'] && $check_field('set','administrator_confirmation')) || (!form['check_in_information_id'] && $check_field('add','administrator_confirmation'))" :disabled="disabledObj['administrator_confirmation_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','administrator_confirmation')">{{form['administrator_confirmation']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "check_in_information_id",
				url_add: "~/api/check_in_information/add?",
				url_set: "~/api/check_in_information/set?",
				url_get_obj: "~/api/check_in_information/get_obj?",
				url_upload: "~/api/check_in_information/upload?",

				query: {
					"check_in_information_id": 0,
				},

				form: {
					"user": 0, // 用户
					"full_name":'', // 姓名
					"contact_information":'', // 联系方式
					"name_of_study_room":'', // 自习室名称
					"appointment_period":'', // 预约时段
					"number_of_appointments":0, // 预约人数
					"administrator_confirmation":'', // 管理员确认
					"check_in_information_id": 0, // ID

				},
				disabledObj:{
					"user_isDisabled": false,
					"full_name_isDisabled": false,
					"contact_information_isDisabled": false,
					"name_of_study_room_isDisabled": false,
					"appointment_period_isDisabled": false,
					"administrator_confirmation_isDisabled": false,
				},
				// 用户列表
				list_user_user: [],

			}
		},
		methods: {
			/**
			 * 获取普通用户用户列表
			 */
			async get_list_user_user() {
                // if(this.user_group !== "管理员" && this.form["user"] === 0) {
                //     this.form["user"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=普通用户");
                if(json.result && json.result.list){
                    this.list_user_user = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
			get_user_user(id){
				var obj = this.list_user_user.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},

			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
				// 获取缓存数据附加
				form = $.db.get("form");
				$.push(this.form ,form);
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){

			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/check_in_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/check_in_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/check_in_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/check_in_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/check_in_information/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
			this.get_list_user_user();
		}
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}
</style>
