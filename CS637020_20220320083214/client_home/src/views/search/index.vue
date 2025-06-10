<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>
				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="自习室资讯"
				source_table="article"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/ordinary_users/list', 'get')"
				:list="result_ordinary_users_user_name"
				title="普通用户用户名"
				source_table="ordinary_users"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/study_room_administrator/list', 'get')"
				:list="result_study_room_administrator_merchant_account_number"
				title="自习室管理员商家账号"
				source_table="study_room_administrator"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/study_room_information/list', 'get')"
				:list="result_study_room_information_name_of_study_room"
				title="自习室信息自习室名称"
				source_table="study_room_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/study_room_information/list', 'get')"
				:list="result_study_room_information_study_room_type"
				title="自习室信息自习室类型"
				source_table="study_room_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/study_room_information/list', 'get')"
				:list="result_study_room_information_region"
				title="自习室信息区域"
				source_table="study_room_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/reservation_information/list', 'get')"
				:list="result_reservation_information_full_name"
				title="预约信息姓名"
				source_table="reservation_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/reservation_information/list', 'get')"
				:list="result_reservation_information_name_of_study_room"
				title="预约信息自习室名称"
				source_table="reservation_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/reservation_information/list', 'get')"
				:list="result_reservation_information_study_room_type"
				title="预约信息自习室类型"
				source_table="reservation_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/reservation_information/list', 'get')"
				:list="result_reservation_information_region"
				title="预约信息区域"
				source_table="reservation_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/check_in_information/list', 'get')"
				:list="result_check_in_information_full_name"
				title="签到信息姓名"
				source_table="check_in_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/check_in_information/list', 'get')"
				:list="result_check_in_information_name_of_study_room"
				title="签到信息自习室名称"
				source_table="check_in_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/use_seat_information/list', 'get')"
				:list="result_use_seat_information_full_name"
				title="使用座位信息姓名"
				source_table="use_seat_information"
			  ></list_result_search>
			  <list_result_search
				v-if="$check_action('/use_seat_information/list', 'get')"
				:list="result_use_seat_information_name_of_study_room"
				title="使用座位信息自习室名称"
				source_table="use_seat_information"
			  ></list_result_search>
			</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
			"result_ordinary_users_user_name":[],
			"result_study_room_administrator_merchant_account_number":[],
			"result_study_room_information_name_of_study_room":[],
			"result_study_room_information_study_room_type":[],
			"result_study_room_information_region":[],
			"result_reservation_information_full_name":[],
			"result_reservation_information_name_of_study_room":[],
			"result_reservation_information_study_room_type":[],
			"result_reservation_information_region":[],
			"result_check_in_information_full_name":[],
			"result_check_in_information_name_of_study_room":[],
			"result_use_seat_information_full_name":[],
			"result_use_seat_information_name_of_study_room":[],
	};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},
	/**
	 * 获取user_name
	 */
	get_ordinary_users_user_name(){
		this.$get("~/api/ordinary_users/get_list?like=0", { page: 1, size: 10, "user_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_ordinary_users_user_name = json.result.list;
			result_ordinary_users_user_name.map(o => o.title = o['user_name'])
	  			this.result_ordinary_users_user_name = result_ordinary_users_user_name
		 	}
		});
	},
	/**
	 * 获取merchant_account_number
	 */
	get_study_room_administrator_merchant_account_number(){
		this.$get("~/api/study_room_administrator/get_list?like=0", { page: 1, size: 10, "merchant_account_number": this.query.word }, (json) => {
		  if (json.result) {
			var result_study_room_administrator_merchant_account_number = json.result.list;
			result_study_room_administrator_merchant_account_number.map(o => o.title = o['merchant_account_number'])
	  			this.result_study_room_administrator_merchant_account_number = result_study_room_administrator_merchant_account_number
		 	}
		});
	},
	/**
	 * 获取name_of_study_room
	 */
	get_study_room_information_name_of_study_room(){
		this.$get("~/api/study_room_information/get_list?like=0", { page: 1, size: 10, "name_of_study_room": this.query.word }, (json) => {
		  if (json.result) {
			var result_study_room_information_name_of_study_room = json.result.list;
			result_study_room_information_name_of_study_room.map(o => o.title = o['name_of_study_room'])
	  			this.result_study_room_information_name_of_study_room = result_study_room_information_name_of_study_room
		 	}
		});
	},
	/**
	 * 获取study_room_type
	 */
	get_study_room_information_study_room_type(){
		this.$get("~/api/study_room_information/get_list?like=0", { page: 1, size: 10, "study_room_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_study_room_information_study_room_type = json.result.list;
			result_study_room_information_study_room_type.map(o => o.title = o['study_room_type'])
	  			this.result_study_room_information_study_room_type = result_study_room_information_study_room_type
		 	}
		});
	},
	/**
	 * 获取region
	 */
	get_study_room_information_region(){
		this.$get("~/api/study_room_information/get_list?like=0", { page: 1, size: 10, "region": this.query.word }, (json) => {
		  if (json.result) {
			var result_study_room_information_region = json.result.list;
			result_study_room_information_region.map(o => o.title = o['region'])
	  			this.result_study_room_information_region = result_study_room_information_region
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_reservation_information_full_name(){
		this.$get("~/api/reservation_information/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_reservation_information_full_name = json.result.list;
			result_reservation_information_full_name.map(o => o.title = o['full_name'])
	  			this.result_reservation_information_full_name = result_reservation_information_full_name
		 	}
		});
	},
	/**
	 * 获取name_of_study_room
	 */
	get_reservation_information_name_of_study_room(){
		this.$get("~/api/reservation_information/get_list?like=0", { page: 1, size: 10, "name_of_study_room": this.query.word }, (json) => {
		  if (json.result) {
			var result_reservation_information_name_of_study_room = json.result.list;
			result_reservation_information_name_of_study_room.map(o => o.title = o['name_of_study_room'])
	  			this.result_reservation_information_name_of_study_room = result_reservation_information_name_of_study_room
		 	}
		});
	},
	/**
	 * 获取study_room_type
	 */
	get_reservation_information_study_room_type(){
		this.$get("~/api/reservation_information/get_list?like=0", { page: 1, size: 10, "study_room_type": this.query.word }, (json) => {
		  if (json.result) {
			var result_reservation_information_study_room_type = json.result.list;
			result_reservation_information_study_room_type.map(o => o.title = o['study_room_type'])
	  			this.result_reservation_information_study_room_type = result_reservation_information_study_room_type
		 	}
		});
	},
	/**
	 * 获取region
	 */
	get_reservation_information_region(){
		this.$get("~/api/reservation_information/get_list?like=0", { page: 1, size: 10, "region": this.query.word }, (json) => {
		  if (json.result) {
			var result_reservation_information_region = json.result.list;
			result_reservation_information_region.map(o => o.title = o['region'])
	  			this.result_reservation_information_region = result_reservation_information_region
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_check_in_information_full_name(){
		this.$get("~/api/check_in_information/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_check_in_information_full_name = json.result.list;
			result_check_in_information_full_name.map(o => o.title = o['full_name'])
	  			this.result_check_in_information_full_name = result_check_in_information_full_name
		 	}
		});
	},
	/**
	 * 获取name_of_study_room
	 */
	get_check_in_information_name_of_study_room(){
		this.$get("~/api/check_in_information/get_list?like=0", { page: 1, size: 10, "name_of_study_room": this.query.word }, (json) => {
		  if (json.result) {
			var result_check_in_information_name_of_study_room = json.result.list;
			result_check_in_information_name_of_study_room.map(o => o.title = o['name_of_study_room'])
	  			this.result_check_in_information_name_of_study_room = result_check_in_information_name_of_study_room
		 	}
		});
	},
	/**
	 * 获取full_name
	 */
	get_use_seat_information_full_name(){
		this.$get("~/api/use_seat_information/get_list?like=0", { page: 1, size: 10, "full_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_use_seat_information_full_name = json.result.list;
			result_use_seat_information_full_name.map(o => o.title = o['full_name'])
	  			this.result_use_seat_information_full_name = result_use_seat_information_full_name
		 	}
		});
	},
	/**
	 * 获取name_of_study_room
	 */
	get_use_seat_information_name_of_study_room(){
		this.$get("~/api/use_seat_information/get_list?like=0", { page: 1, size: 10, "name_of_study_room": this.query.word }, (json) => {
		  if (json.result) {
			var result_use_seat_information_name_of_study_room = json.result.list;
			result_use_seat_information_name_of_study_room.map(o => o.title = o['name_of_study_room'])
	  			this.result_use_seat_information_name_of_study_room = result_use_seat_information_name_of_study_room
		 	}
		});
	},

  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
		this.get_ordinary_users_user_name();
		this.get_study_room_administrator_merchant_account_number();
		this.get_study_room_information_name_of_study_room();
		this.get_study_room_information_study_room_type();
		this.get_study_room_information_region();
		this.get_reservation_information_full_name();
		this.get_reservation_information_name_of_study_room();
		this.get_reservation_information_study_room_type();
		this.get_reservation_information_region();
		this.get_check_in_information_full_name();
		this.get_check_in_information_name_of_study_room();
		this.get_use_seat_information_full_name();
		this.get_use_seat_information_name_of_study_room();
  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
	  this.get_ordinary_users_user_name();
	  this.get_study_room_administrator_merchant_account_number();
	  this.get_study_room_information_name_of_study_room();
	  this.get_study_room_information_study_room_type();
	  this.get_study_room_information_region();
	  this.get_reservation_information_full_name();
	  this.get_reservation_information_name_of_study_room();
	  this.get_reservation_information_study_room_type();
	  this.get_reservation_information_region();
	  this.get_check_in_information_full_name();
	  this.get_check_in_information_name_of_study_room();
	  this.get_use_seat_information_full_name();
	  this.get_use_seat_information_name_of_study_room();
	},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
