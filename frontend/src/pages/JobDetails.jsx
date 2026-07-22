import { useEffect, useState } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getJobById } from "../services/jobService";
import { applyJob } from "../services/applicationService";

function JobDetails() {
  const { id } = useParams();
  const navigate = useNavigate();

  const [job, setJob] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const loadJob = async () => {
      try {
        const data = await getJobById(id);
        setJob(data);
      } catch (error) {
        console.error("Error loading job:", error);
      }
    };

    loadJob();
  }, [id]);

  const handleApply = async () => {
    try {
      setLoading(true);

      const result = await applyJob(id);

      console.log(result);

      alert("Job Applied Successfully!");

    } catch (error) {
      console.error("Apply Error:", error);

      if (error.response) {
        alert(error.response.data);
      } else {
        alert("Failed to apply for job.");
      }
    } finally {
      setLoading(false);
    }
  };

  if (!job) {
    return (
      <h2 style={{ textAlign: "center", marginTop: "40px" }}>
        Loading...
      </h2>
    );
  }

  return (
    <div
      style={{
        maxWidth: "800px",
        margin: "40px auto",
        padding: "30px",
        border: "1px solid #ddd",
        borderRadius: "10px",
        boxShadow: "0 2px 8px rgba(0,0,0,0.1)",
      }}
    >
      <h1>{job.title}</h1>

      <hr />

      <p>
        <strong>Company:</strong> {job.company}
      </p>

      <p>
        <strong>Location:</strong> {job.location}
      </p>

      <p>
        <strong>Salary:</strong> {job.salary}
      </p>

      <p>
        <strong>Job Type:</strong>{" "}
        {job.jobType || "Not Specified"}
      </p>

      <p>
        <strong>Description:</strong>
      </p>

      <p>{job.description}</p>

      <div style={{ marginTop: "25px" }}>
        <button
          onClick={() => navigate("/jobs")}
          style={{
            padding: "10px 20px",
            marginRight: "10px",
            cursor: "pointer",
            backgroundColor: "#6c757d",
            color: "white",
            border: "none",
            borderRadius: "5px",
          }}
        >
          Back
        </button>

        <button
          onClick={handleApply}
          disabled={loading}
          style={{
            padding: "10px 20px",
            cursor: loading ? "not-allowed" : "pointer",
            backgroundColor: "#28a745",
            color: "white",
            border: "none",
            borderRadius: "5px",
            opacity: loading ? 0.7 : 1,
          }}
        >
          {loading ? "Applying..." : "Apply Now"}
        </button>
      </div>
    </div>
  );
}

export default JobDetails;