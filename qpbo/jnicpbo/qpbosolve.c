#include "ciface.h"
#include "qpbosolve.h"


	void qpbo_solve(qpbosolveinput *input,qpbosolveoutput* output)
	{		
		if (input==NULL) {
			return NULL;
		}
				
		if (2*input->edges > input->nodes * (input->nodes-1)) {
			output->valid=0;
			return;
		}
		void *q=create_qpbo(input->nodes, input->edges);
		
		for(int i=0;i<input->nodes;i++){
			add_unary_energies(q,i, input->unaryenergies[i].e_0,input->unaryenergies[i].e_1);			
		}
		for(int k=0;k<input->edges;k++){
			add_pairwise_energies(q,input->pairwiseenergies[k].x, input->pairwiseenergies[k].y, 
			input->pairwiseenergies[k].e_0_0,
			input->pairwiseenergies[k].e_0_1,
			input->pairwiseenergies[k].e_1_0,
			input->pairwiseenergies[k].e_1_1);
		}

		find_solution(q);
		
		for(int i=0;i<input->nodes;i++){
			output->flags[i]=get_flag(q,i);
		}
		
		dispose_qpbo(q);
	}