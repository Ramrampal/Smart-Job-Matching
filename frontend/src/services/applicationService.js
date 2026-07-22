import api from "./api";

export const applyJob = async (jobId) => {
  const userId = localStorage.getItem("userId");

  const application = {
    user: {
      id: Number(userId),
    },
    job: {
      id: Number(jobId),
    },
    resume: "",
    status: "Applied",
  };

  const response = await api.post("/applications", application);

  return response.data;
};

export const getMyApplications = async () => {
  const response = await api.get("/applications");
  return response.data;
};