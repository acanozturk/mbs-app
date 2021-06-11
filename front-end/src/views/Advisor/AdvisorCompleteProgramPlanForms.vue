<template>
  <div class="app">
    <left-menu-advisor/>

    <div id="AdvisorCompleteProgramPlanForms">
      <div>
        <h1>Complete Program Plan Form</h1>
      </div>
      <div><label>
        <select name="student" v-model="selectedStudent" size="Number_of_options">
          <option v-for="(student, keyStudent) in students" :value="student" :key="keyStudent">{{ student.firstName }}
            {{ student.lastName }}
          </option>
        </select>
      </label>
        <button type="button" v-on:click="getSelectedStudentsInformation()" id="SelectStudentButton">Select Student
        </button>
      </div>

      <div>
        <p>
          Student: {{ this.selectedStudent.firstName }} {{ this.selectedStudent.lastName }}
        </p>
        <p>
          Program Plan Form Status: {{ this.programPlanFormStatus }}
        </p>
        <p>
          Thesis Topic: {{ this.thesisTopic }}
        </p>
      </div>
      <div>
        <button type="button" v-on:click="completeForm()" id="completeForm">Complete Program Plan Form</button>
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
  name: 'AdvisorCompleteProgramPlanForms',
  data() {
    return {
      students: [],
      selectedStudent: "",
      response: "",
      programPlanFormStatus: "",
      thesisTopic: "",
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
    getSelectedStudentsInformation() {
      this.clearError();
      console.log(this.selectedStudent);
      axios({
        method: "GET",
        "url": `http://localhost:8080/mbs/programPlanForm/getByStudent/${this.selectedStudent.id}`
      }) //This request is not needed -Baran
          .then(result => {
            console.log(result.data);
            this.programPlanFormStatus = result.data.programPlanFormStatus;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
      axios({
        method: "GET",
        "url": `http://localhost:8080/mbs/students/${this.selectedStudent.id}`
      }) //This request is not needed -Baran
          .then(result => {
            console.log(result.data);
            this.thesisTopic = result.data.student.thesisTopic;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    completeForm() {
      axios({
        method: "PUT",
        "url": `http://localhost:8080//mbs/programPlanForm/complete/${this.selectedStudent.id}`
      }) //This request is not needed -Baran
          .then(result => {
            console.log(result.data);
            this.programPlanFormStatus = "COMPLETED";
            this.response = result.data.successMessage;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    }
  }
}
</script>

<style scoped>
#AdvisorCompleteProgramPlanForms {
  width: 1000px;
  height: 600px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}

#SelectStudentButton {
  margin-top: 10px;
  margin-left: 10px;

}
</style>