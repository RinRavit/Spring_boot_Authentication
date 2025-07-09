package Authentication_Service.Authentication.service;

import Authentication_Service.Authentication.entity.Contract;
import Authentication_Service.Authentication.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContractService {

    @Autowired
    private ContractRepository contractRepository;

    // Create
    public Contract createContract(Contract contract) {
        if (contractRepository.existsByContractNo(contract.getcontractNo())) {
            throw new RuntimeException("Contract already exists");
        }
        return contractRepository.save(contract);
    }

    // List all
    public List<Contract> getAllContracts() {
        return contractRepository.findAll();
    }

    // Update
    public Contract updateContract(Long id, Contract updatedData) {
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
        // existingContract.setNameProject(updatedData.getNameProject());
        // existingContract.setAmountBudget(updatedData.getAmountBudget());
        // existingContract.setStatusContract(updatedData.getStatusContract());
        // existingContract.setDate(updatedData.getDate());
        // existingContract.setCreateBycontractor(updatedData.getCreateBycontractor());
    
        return contractRepository.save(existingContract);
    }

    // List by Id
    public Contract getContractById(Long id) {
        return contractRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with ID: " + id));
    }
    
    // Delete
    public void deleteContract(Long id) {
        if (!contractRepository.existsById(id)) {
            throw new RuntimeException("Contract not found with ID: " + id);
        }
        contractRepository.deleteById(id);
    }
    
}
