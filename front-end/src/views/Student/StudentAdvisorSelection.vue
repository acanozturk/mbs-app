<template>
  <div class="app">
    <LeftMenuStudent/>
    <div id="StudentAdvisorSelection">
      <div>
        <h1>Advisor Selection </h1>
      </div>
      <select name="selectedAdvisor" v-model="selectedAdvisor">
        <option v-for="(advisor, keyAdvisor) in this.advisors" :value="advisor" :key="keyAdvisor">{{
            advisor.firstName
          }} {{ advisor.lastName }}
        </option>
      </select>
      <div>
        <button type="button" style="margin-top:10px" v-on:click="proposeToAnAdvisor()">Propose to this Advisor</button>
      </div>
      <div>
        {{ this.response }}
      </div>
    </div>
  </div>
</template>

<script>
import LeftMenuStudent from './StudentLeftMenu.vue';
import axios from "axios";

export default {
  components: {LeftMenuStudent},
  name: 'StudentAdvisorSelection',
  data() {
    return {
      advisors: [],
      selectedAdvisor: null,
      input: {
        studentId: "",
        advisorIds: [],
      },
      response: "",
    };
  },
  mounted() {
    console.log(this.$store.state.user.id);
    axios({
      method: "GET",
      "url": `http://localhost:8080/mbs/students/getRecommendedAdvisors/${this.$store.state.user.id}`
    }).then(result => {
      console.log(result.data);
      if (result.data.recommendations != null) {
        result.data.recommendations.forEach(element => {
          if (element.advisor != null) {
            this.advisors.push(element.advisor);
          }
        });
      }

    }, error => {
      console.error(error.response);
    });
    this.advisors.push({firstName: "mock Adv. FN", lastName: "mock Adv. LN", id: "mock_recc_adv_id"});
  },
  methods: {
    clearError: function () {
      this.response = "";
    },
    proposeToAnAdvisor() {
      this.clearError();
      console.log(this.selectedAdvisor);
      this.input.studentId = this.$store.state.user.id;
      this.input.advisorIds.push(this.selectedAdvisor.id);

      axios({
        method: "PUT", "url": `http://localhost:8080/mbs/students/proposeAdvisors/${this.$store.state.user.id}`,
        "data": this.input, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            console.log(result.data);
            this.response = result.data.successMessage;
          }, error => {
            this.response = error.response.data.errorMessage;
            console.log(error.response);
            console.error(error);
          });

    }
  }
}
</script>

<style scoped>
#StudentAdvisorSelection {
  width: 1000px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0px;
}
</style>