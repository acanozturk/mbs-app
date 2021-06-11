<template>
  <div class="app">
    <left-menu-advisor/>

    <div id="AdvisorFinalThesisCopyDeadlineExtension">
      <div>
        <h1>Deadline Extension</h1>
      </div>
      <div>
        <button type="button" v-on:click="selectStudent()" style="margin-top:10px">Select Student</button>
      </div>
      <label>
        <select name="student" v-model="selectedstudent" size="Number_of_options">
          <option v-for="(student, keyStudent) in students" :value="student" :key="keyStudent">{{ student.firstName }}
            {{ student.lastName }}
          </option>
        </select>
      </label>
      <div>
        <p>
          Student: {{ this.input.firstName }} {{ this.input.lastName }}
        <p>
          Program Plan Form Status : {{ this.input.programPlanFormStatus }}
        <p>
          Thesis Topic : {{ this.input.thesisTopic }}
        </p>
      </div>
      <div>
        <button type="button" v-on:click="extendDeadline()" style="margin-top:10px">Extend Deadline</button>
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
  name: 'AdvisorFinalThesisCopyDeadlineExtension',
  data() {
    return {
      selectedstudent: null,
      students: [],
      input: {
        studentId: "",
        programPlanFormStatus: "",
        thesisTopic: "",
        firstName: "",
        lastName: ""
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
      this.input.studentId = this.selectedstudent.id;
      if (this.selectedstudent.programPlanForm != null) {
        this.input.programPlanFormStatus = this.selectedstudent.programPlanForm.programPlanFormStatus;
      } else {
        this.input.programPlanFormStatus = "";
      }
      if (this.selectedstudent.thesisTopic != null) {
        this.input.thesisTopic = this.selectedstudent.thesisTopic;
      } else {
        this.input.thesisTopic = "";
      }
      this.input.firstName = this.selectedstudent.firstName;
      this.input.lastName = this.selectedstudent.lastName;
    },

    extendDeadline() {
      this.clearError();
      console.log(this.input.studentId);
      axios({
        method: "PUT", "url": `http://localhost:8080/mbs/thesis/extendDeadline`,
        "data": this.input.studentId, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            console.log(result.data);
            this.response = result.data.successMessage;
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });


    }
  }

}
</script>

<style scoped>
#AdvisorFinalThesisCopyDeadlineExtension {
  width: 1000px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>