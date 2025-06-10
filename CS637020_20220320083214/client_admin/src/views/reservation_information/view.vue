<template>
	<el-main class="bg">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">
			<el-col v-if="user_group === '管理员' || $check_field('get','user') || $check_field('add','user') || $check_field('set','user')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="用户" prop="user">
						<div v-if="user_group !== '管理员'">
							{{ get_user_session_user(form['user']) }}
							<!--<el-input id="business_name" v-model="form['user']" placeholder="请输入用户"-->
							<!--v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','user')) || (!form['reservation_information_id'] && $check_field('add','user'))" :disabled="disabledObj['user_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','user')">{{form['user']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','user')) || (!form['reservation_information_id'] && $check_field('add','user'))" id="user" v-model="form['user']" :disabled="disabledObj['user_isDisabled']">
								<el-option v-for="o in list_user_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','user')" id="user" v-model="form['user']" :disabled="true">
								<el-option v-for="o in list_user_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="user" v-model="form['user']" :disabled="disabledObj['user_isDisabled']">
							<el-option v-for="o in list_user_user" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','full_name') || $check_field('add','full_name') || $check_field('set','full_name')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="姓名" prop="full_name">
					<el-input id="full_name" v-model="form['full_name']" placeholder="请输入姓名"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','full_name')) || (!form['reservation_information_id'] && $check_field('add','full_name'))" :disabled="disabledObj['full_name_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','full_name')">{{form['full_name']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','contact_information') || $check_field('add','contact_information') || $check_field('set','contact_information')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="联系方式" prop="contact_information">
					<el-input id="contact_information" v-model="form['contact_information']" placeholder="请输入联系方式"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','contact_information')) || (!form['reservation_information_id'] && $check_field('add','contact_information'))" :disabled="disabledObj['contact_information_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','contact_information')">{{form['contact_information']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','name_of_study_room') || $check_field('add','name_of_study_room') || $check_field('set','name_of_study_room')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="自习室名称" prop="name_of_study_room">
					<el-input id="name_of_study_room" v-model="form['name_of_study_room']" placeholder="请输入自习室名称"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','name_of_study_room')) || (!form['reservation_information_id'] && $check_field('add','name_of_study_room'))" :disabled="disabledObj['name_of_study_room_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','name_of_study_room')">{{form['name_of_study_room']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','study_room_type') || $check_field('add','study_room_type') || $check_field('set','study_room_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="自习室类型" prop="study_room_type">
					<el-select id="study_room_type" v-model="form['study_room_type']"
						v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','study_room_type')) || (!form['reservation_information_id'] && $check_field('add','study_room_type'))">
						<el-option v-for="o in list_study_room_type" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','study_room_type')">{{form['study_room_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','region') || $check_field('add','region') || $check_field('set','region')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="区域" prop="region">
					<el-input id="region" v-model="form['region']" placeholder="请输入区域"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','region')) || (!form['reservation_information_id'] && $check_field('add','region'))" :disabled="disabledObj['region_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','region')">{{form['region']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','opening_hours') || $check_field('add','opening_hours') || $check_field('set','opening_hours')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="开放时间" prop="opening_hours">
					<el-input id="opening_hours" v-model="form['opening_hours']" placeholder="请输入开放时间"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','opening_hours')) || (!form['reservation_information_id'] && $check_field('add','opening_hours'))" :disabled="disabledObj['opening_hours_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','opening_hours')">{{form['opening_hours']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','administrator_telephone') || $check_field('add','administrator_telephone') || $check_field('set','administrator_telephone')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="管理员电话" prop="administrator_telephone">
					<el-input id="administrator_telephone" v-model="form['administrator_telephone']" placeholder="请输入管理员电话"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','administrator_telephone')) || (!form['reservation_information_id'] && $check_field('add','administrator_telephone'))" :disabled="disabledObj['administrator_telephone_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','administrator_telephone')">{{form['administrator_telephone']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','address') || $check_field('add','address') || $check_field('set','address')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="地址" prop="address">
					<el-input id="address" v-model="form['address']" placeholder="请输入地址"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','address')) || (!form['reservation_information_id'] && $check_field('add','address'))" :disabled="disabledObj['address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','address')">{{form['address']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','hourly_rate') || $check_field('add','hourly_rate') || $check_field('set','hourly_rate')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="每小时价格" prop="hourly_rate">
					<el-input id="hourly_rate" v-model="form['hourly_rate']" placeholder="请输入每小时价格"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','hourly_rate')) || (!form['reservation_information_id'] && $check_field('add','hourly_rate'))" :disabled="disabledObj['hourly_rate_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','hourly_rate')">{{form['hourly_rate']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','seat_type') || $check_field('add','seat_type') || $check_field('set','seat_type')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="座位类型" prop="seat_type">
					<el-input id="seat_type" v-model="form['seat_type']" placeholder="请输入座位类型"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','seat_type')) || (!form['reservation_information_id'] && $check_field('add','seat_type'))" :disabled="disabledObj['seat_type_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','seat_type')">{{form['seat_type']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','appointment_period') || $check_field('add','appointment_period') || $check_field('set','appointment_period')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约时段" prop="appointment_period">
					<el-input id="appointment_period" v-model="form['appointment_period']" placeholder="请输入预约时段"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','appointment_period')) || (!form['reservation_information_id'] && $check_field('add','appointment_period'))" :disabled="disabledObj['appointment_period_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','appointment_period')">{{form['appointment_period']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','number_of_appointments') || $check_field('add','number_of_appointments') || $check_field('set','number_of_appointments')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="预约人数" prop="number_of_appointments">
					<el-input-number id="number_of_appointments" v-model.number="form['number_of_appointments']"
						v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','number_of_appointments')) || (!form['reservation_information_id'] && $check_field('add','number_of_appointments'))"></el-input-number>
					<div v-else-if="$check_field('get','number_of_appointments')">{{form['number_of_appointments']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','service_duration') || $check_field('add','service_duration') || $check_field('set','service_duration')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="使用时长" prop="service_duration">
					<el-input-number id="service_duration" v-model.number="form['service_duration']"
						v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','service_duration')) || (!form['reservation_information_id'] && $check_field('add','service_duration'))"></el-input-number>
					<div v-else-if="$check_field('get','service_duration')">{{form['service_duration']}}</div>
				</el-form-item>
			</el-col>
			<el-col v-if="user_group === '管理员' || $check_field('get','cost') || $check_field('add','cost') || $check_field('set','cost')" :xs="24" :sm="12" :lg="8">
				<el-form-item label="费用" prop="cost">
					<el-input id="cost" v-model="form['cost']" placeholder="请输入费用"
							  v-if="user_group === '管理员' || (form['reservation_information_id'] && $check_field('set','cost')) || (!form['reservation_information_id'] && $check_field('add','cost'))"  @focus="set_cost()" :disabled="disabledObj['cost_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','cost')">{{form['cost']}}</div>
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
				field: "reservation_information_id",
				url_add: "~/api/reservation_information/add?",
				url_set: "~/api/reservation_information/set?",
				url_get_obj: "~/api/reservation_information/get_obj?",
				url_upload: "~/api/reservation_information/upload?",

				query: {
					"reservation_information_id": 0,
				},

				form: {
					"user": 0, // 用户
					"full_name":'', // 姓名
					"contact_information":'', // 联系方式
					"name_of_study_room":'', // 自习室名称
					"study_room_type":'', // 自习室类型
					"region":'', // 区域
					"opening_hours":'', // 开放时间
					"administrator_telephone":'', // 管理员电话
					"address":'', // 地址
					"hourly_rate":'', // 每小时价格
					"seat_type":'', // 座位类型
					"appointment_period":'', // 预约时段
					"number_of_appointments":0, // 预约人数
					"service_duration":0, // 使用时长
					"cost":'', // 费用
					"reservation_information_id": 0, // ID

				},
				disabledObj:{
					"user_isDisabled": false,
					"full_name_isDisabled": false,
					"contact_information_isDisabled": false,
					"name_of_study_room_isDisabled": false,
					"study_room_type_isDisabled": false,
					"region_isDisabled": false,
					"opening_hours_isDisabled": false,
					"administrator_telephone_isDisabled": false,
					"address_isDisabled": false,
					"hourly_rate_isDisabled": false,
					"seat_type_isDisabled": false,
					"appointment_period_isDisabled": false,
					"cost_isDisabled": false,
				},
				// 用户列表
				list_user_user: [],
				// 用户组
				group_user_user: "",
				//自习室类型选项列表
				list_study_room_type: ['付费自习室','免费自习室'],

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
			/**
			 * 获取普通用户用户组
			 */
			async get_group_user_user() {
				this.form["user"] = this.user.user_id;
				var json = await this.$get("~/api/user_group/get_obj?name=普通用户");
				if(json.result && json.result.obj){
					this.group_user_user = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_user(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_user.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
						for (let key in _this.form) {
							arrForm.push(key)
						}
						for (var i=0;i<arr.length;i++){
							for (var j=0;j<arrForm.length;j++){
								if (arr[i]===arrForm[j]){
									if (arr[i]!=="user") {
										_this.form[arrForm[j]] = res.result.obj[arr[i]]
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
										break;
									}else {
										_this.disabledObj[arrForm[j] + '_isDisabled'] = true
									}
								}
							}
						}
					}
				});
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
			set_cost(){
				this.form.cost =this.form.service_duration * this.form.number_of_appointments * this.form.hourly_rate
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
				/**
				* 请求列表前
				* @param {Object} param
				*/
				var user_group = this.user.user_group;
				if (user_group !== "管理员") {
					switch (user_group) {
						case "用户":
							if(param["user"] > 0){
								param["user"] = this.user.user_id;
							}
							break;
					}
				}
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
					bl = this.$check_action('/reservation_information/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/reservation_information/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/reservation_information/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/reservation_information/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/reservation_information/view','get');
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
			this.get_group_user_user();
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
