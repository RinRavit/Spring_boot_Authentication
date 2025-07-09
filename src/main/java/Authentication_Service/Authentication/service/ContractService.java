package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Contract;
import Authentication_Service.Authentication.entity.Project;
import Authentication_Service.Authentication.repository.ContractRepository;
import Authentication_Service.Authentication.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ProjectRepository projectRepository;

    // Create
    public Contract createContract(Contract contract, String projectNo) {
        if (contractRepository.existsByContractNo(contract.getcontractNo())) {
            throw new RuntimeException("Contract already exists");
        }

        if (contractRepository.findByProjectNoProject(projectNo).isPresent()) {
            throw new RuntimeException(" This projectNo is already used in another contract.");
        }
    
        Project project = projectRepository.findByNoProject(projectNo)
            .orElseThrow(() -> new RuntimeException("Project not found with noProject: " + projectNo));
    
        contract.setProject(project);
        return contractRepository.save(contract);
    }

    // List all
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    // Get by ID
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with ID: " + id));
    }

    // Update
    public Contract updateContract(Long id, Contract updatedData, String projectNo) {
        Contract existingContract = contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found"));

                existingContract.setcontractNo(updatedData.getcontractNo());
                        existingContract.settechincalAssistant(updatedData.gettechincalAssistant());
                        existingContract.setcontractorList(updatedData.getcontractorList());
                        existingContract.setDescription(updatedData.getDescription());
                        existingContract.setcompetitivebidding(updatedData.getcompetitivebidding());
                        existingContract.setnumberofbidder(updatedData.getnumberofbidder());
                        existingContract.settypeprocurment(updatedData.gettypeprocurment());
                        existingContract.setnatureOfProject(updatedData.getnatureOfProject());
                        existingContract.setContractsigndate(updatedData.getContractsigndate());
                        existingContract.setGuaranteeperiod(updatedData.getGuaranteeperiod());
                        existingContract.setstartDate(updatedData.getstartDate());
                        existingContract.setcompletionDate(updatedData.getcompletionDate());

        Long projectId = Long.parseLong(projectNo);
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        existingContract.setProject(project);

        return contractRepository.save(existingContract);
    }

    // Delete
    public void deleteContract(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with ID: " + id);
        }
        contractRepository.deleteById(id);
    }
}
