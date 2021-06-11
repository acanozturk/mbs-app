<template>
  <div class="app">
    <left-menu-advisor/>

    <div id="AdvisorUpdateThesisTopics">
      <div>
        <h1>Update Thesis Topic</h1>
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
        <p>
          Selected Student ID : {{ this.input.studentId }}
        <p>
          Thesis Topic : {{ this.input.thesisTopic }}
        </p>
      </div>
      <div>
        <label>
          <input v-model="thesisTopic" placeholder="Write the New Thesis Topic">
        </label>
        <div>
          <button type="button" v-on:click="updateThesisTopic()" style="margin-top:10px">Update Thesis Topic</button>
        </div>
        <p>New Topic is: {{ this.inputUpdateThesisTopic.thesisTopic }}</p>
      </div>
      <div>
        {{ this.response }}
      </div>
    </div>
  </div>
</template>

<script>
import LeftMenuAdvisor from './AdvisorLeftMenu.vue';
import axios from "axios";

export default {
  components: {LeftMenuAdvisor},
  name: 'AdvisorUpdateThesisTopics',
  data() {
    return {
      thesisTopic: "",
      student: "",
      students: [],
      studentList: [],
      input: {
        studentId: "",
        proposalStatus: "",
        programPlanFormStatus: "",
        thesisTopic: "",
      },
      inputUpdateThesisTopic: {
        studentId: "",
        thesisTopic: "",
      },
      response: "",
    };
  },
  mounted() {
    console.log("User ID: " + this.$store.state.user.id);
    axios({
      method: "GET",
      "url": `http://localhost:8080/mbs/advisors/getApprovedStudents/${this.$store.state.user.id}`
    }).then(result => {
      console.log(result.data.studentList);
      if (result.data.studentList != null) {
        result.data.studentList.forEach(element => {
          if (element != null) {
            this.students.push(element);
            //console.log("Approved S.: "+element);
          }
        });
      }
    }, error => {
      console.error(error.response);
    });


  },
  methods: {
    clearError: function () {
      this.response = "";
    },
    selectStudent() {
      this.clearError();
      this.input.studentId = this.student.id;
      if (this.student.proposalStatus != null) {
        this.input.proposalStatus = this.student.proposalStatus;
      } else {
        this.input.proposalStatus = "";
      }
      if (this.student.programPlanFormStatus != null) {
        this.input.programPlanFormStatus = this.student.programPlanFormStatus;
      } else {
        this.input.programPlanFormStatus = "";
      }
      if (this.student.thesisTopic != null) {
        this.input.thesisTopic = this.student.thesisTopic;
      } else {
        this.input.thesisTopic = "";
      }
    },
    updateThesisTopic() {
      this.clearError();
      this.inputUpdateThesisTopic.studentId = this.student.id;
      this.inputUpdateThesisTopic.thesisTopic = this.thesisTopic;

      axios({
        method: "PUT", "url": "http://localhost:8080/mbs/thesis/updateThesisTopic",
        "data": this.inputUpdateThesisTopic, "headers": {"content-type": "application/json"}
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
#AdvisorUpdateThesisTopics {
  width: 1000px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>