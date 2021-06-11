<template>
  <div class="app">
    <left-menu-advisor/>

    <div id="AdvisorStudentApproval">
      <div>
        <h1>Student Approval</h1>
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
          Program Plan Form : {{ this.programPlanFormStatus }}
        </p>
      </div>
      <div>
        <button type="button" v-on:click="approveStudent()" style="margin-top:5px">Approve Student Proposal</button>
      </div>
      <div>
        <button type="button" :disabled='isButtonDisabled' v-on:click="getProgramPlanForm()"
                style="margin-top:10px; margin-left:800px">Get Program Plan Form
        </button>
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
  name: 'AdvisorStudentApproval',
  data() {
    return {
      disabledButton: true,
      programPlanFormStatus: "",
      studentId: "",
      students: [],
      proposals: [],
      student: "",
      selectedAdvisor: null,
      input: {
        advisorId: "",
        studentIds: [],
      },
      response: "",
    };
  },
  computed: {
    isButtonDisabled: function () {
      return this.disabledButton;
    },
  },
  methods: {
    clearError: function () {
      this.response = "";
    },
    selectStudent() {
      this.clearError();
      this.studentId = this.student.id;
      if (this.student.programPlanForm != null) {
        this.programPlanFormStatus = this.student.programPlanForm.programPlanFormStatus;
        this.disabledButton = false;
      }
    },
    getProgramPlanForm() {
      this.clearError();
      axios({method: "GET", "url": `http://localhost:8080/mbs/programPlanForm/getByStudent/${this.studentId}`})
          .then(result => {
            console.log(result.data);
            window.open(result.data.url);
          }).catch(error => {
        this.response = `${error.response.data.errorMessage}`
      });
    },
    approveStudent() {
      this.clearError();
      console.log(this.student);
      this.input.studentIds.push(this.studentId);
      axios({
        method: "PUT", "url": `http://localhost:8080/mbs/advisors/approveStudents/${this.$store.state.user.id}`,
        "data": this.input, "headers": {"content-type": "application/json"}
      })
          .then(result => {
            console.log(result.data);
            this.response = result.data.successMessage;
          }, error => {
            this.response = `${error.response.data.errorMessage}`
            console.log(error.response);
            console.error(error);
          });
    }
  },
  mounted() {
    console.log("User ID: " + this.$store.state.user.id);
    axios({
      method: "GET",
      "url": `http://localhost:8080/mbs/advisors/getProposals/${this.$store.state.user.id}`
    }).then(result => {
      console.log(result.data.proposals);
      if (result.data.proposals != null) {
        result.data.proposals.forEach(element => {
          if (element.student != null) {
            this.students.push(element.student);
          }
        });
      }
    }, error => {
      console.error(error);
    });
  },
}
</script>

<style scoped>
#AdvisorStudentApproval {
  width: 1000px;
  border: 1px solid #CCCCCC;
  background-color: #FFFFFF;
  margin-top: 10px;
  margin-left: auto;
  padding: 0;
}
</style>