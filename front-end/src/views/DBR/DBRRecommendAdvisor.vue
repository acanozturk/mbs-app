<template>
  <div class="app">
    <leftMenuDBR/>
    <div id="DBRRecommendAdvisor">
      <div>
        <h1>Recommend Advisors</h1>
      </div>
      <div>
        <button type="button" v-on:click="selectStudent()" style="margin-top:10px">Select Student</button>
      </div>
      <label>
        <select name="student" v-model="student" size="Number_of_options">
          <option v-for="(student, keyStudent) in students" :value="student" :key="keyStudent">{{ student.firstName }}
            {{ student.lastName }}
          </option>
        </select>
      </label>
      <div>
        <label>
          <select multiple name="advisors" v-model="advisors" size="5" style="margin-top:50px; margin-left:500px">
            <option v-for="(advisor, keyAdvisor) in advisors" :value="advisor" :key="keyAdvisor">{{ advisor.firstName }}
              {{ advisor.lastName }}
            </option>
          </select>
        </label>
        <button type="button" v-on:click="putRecommendedAdvisors()" style="margin-top:10px; margin-left:500px">
          Recommend
        </button>
        <div class="response">{{ this.response }}</div>
      </div>
    </div>
  </div>
</template>

<script>
import leftMenuDBR from './DBRLeftMenu.vue';
import axios from "axios";

export default {
  components: {leftMenuDBR},
  name: 'DBRRecommendAdvisor',
  data() {
    return {
      advisor: {
        firstName: "",
        id: "",
        lastName: "",
        userType: ""
      },
      advisors: [],

      student: {
        firstName: "",
        id: "",
        lastName: "",
        userType: ""
      },
      students: [],
      advisorList: [],
      advisorIds: [],
      studentId: "",
      input: {},
      response: '',
    };
  },
  methods: {
    clearError: function () {
      this.response = "";
    },
    selectStudent() {
      this.clearError();
      this.input.studentId = this.student.id;
    },
    putRecommendedAdvisors() {
      this.clearError();
      console.log(this.student.id);
      console.log(this.advisors);

      this.advisors.forEach(element => {
        this.advisorIds.push(element.id);
      });
      this.input.advisorIds = this.advisorIds;
      console.log(this.input);
      axios({
        method: "PUT",
        "url": "http://localhost:8080/mbs/dbrs/recommendAdvisor",
        "data": this.input,
        "headers": {"content-type": "application/json"}
      })
          .then(result => {
            console.log(result.data);
            this.response = result.data.successMessage;
          }).catch(error => {
            console.log(error.response)
            this.response = `${error.response.data.errorMessage}`
          }
      );
      this.advisorIds = [];
    }
  },

  mounted() {
    axios({method: "GET", "url": "http://localhost:8080/mbs/dbrs/getStudentWithNoAdvisor"}).then(result => {
      console.log(result.data.studentList);
      this.students = result.data.studentList

    }, error => {
      console.error(error);
    });
    axios({method: "GET", "url": "http://localhost:8080/mbs/dbrs/getAdvisors"}).then(result => {
      console.log(result.data.advisorList);
      this.advisors = result.data.advisorList
    }, error => {
      console.error(error);
    });
  },
}
</script>

<style scoped>
#DBRRecommendAdvisor {
  width: 500px;
  border: 10px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: 0;
  padding: 200px;
}
</style>